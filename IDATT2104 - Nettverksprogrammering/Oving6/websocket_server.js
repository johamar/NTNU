const crypto = require('crypto');
const net = require('net');


// Simple HTTP server responds with a simple WebSocket client test
const httpServer = net.createServer((connection) => {
  connection.on('data', () => {
    let content = `<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>WebSocket Test</title>
</head>
<body>
    <h1>WebSocket Client</h1>
    <canvas id="drawCanvas" width="500" height="500" style="border:1px solid black;"></canvas>
    <button onclick="clearCanvas()">Clear</button>


    <script>
        let ws = new WebSocket('ws://localhost:3001');

        ws.onopen = () => console.log("Connected to WebSocket server");

        ws.onmessage = (event) => {
            let {x, y, type} = JSON.parse(event.data);
            if (type === "clear") {
                clearCanvas();
                return;
            } else {
                drawFromServer(x, y, type);
            }
        };

        function sendMessage() {
            let msg = document.getElementById("message").value;
            ws.send(msg);
        }
        const canvas = document.getElementById("drawCanvas");
        const ctx = canvas.getContext("2d");

        let drawing = false;

        function startPosition(e) {
            drawing = true;
            draw(e, "start");
        }

        function endPosition() {
            drawing = false;
            ctx.beginPath();
        }

        function draw(e, type = "move") {
            if (!drawing) return;

            const x = e.clientX - canvas.offsetLeft;
            const y = e.clientY - canvas.offsetTop;

            ctx.lineWidth = 3;
            ctx.lineCap = "round";
            ctx.strokeStyle = "black";

            ctx.lineTo(x, y);
            ctx.stroke();
            ctx.beginPath();
            ctx.moveTo(x, y);

            ws.send(JSON.stringify({x, y, type}));
        }

        function drawFromServer(x, y, type) {
            if (type === "start") {
                ctx.beginPath();
                ctx.moveTo(x, y);
            } else {
                ctx.lineTo(x, y);
                ctx.stroke();
                ctx.beginPath();
                ctx.moveTo(x, y);
            }
        }
        function clearCanvas() {
            ctx.clearRect(0, 0, canvas.width, canvas.height);
            ws.send(JSON.stringify({type: "clear"}));
        }

        canvas.addEventListener("mousedown", startPosition);
        canvas.addEventListener("mouseup", endPosition);
        canvas.addEventListener("mousemove", draw);
    </script>
</body>
</html>

`;
    connection.write('HTTP/1.1 200 OK\r\nContent-Length: ' + content.length + '\r\n\r\n' + content);
  });
});
httpServer.listen(3000, () => {
  console.log('HTTP server listening on port 3000');
});

// Incomplete WebSocket server
const clients = new Set();

const wsServer = net.createServer((connection) => {
  console.log('Client connected');
  
  connection.on('data', (data) => {
    if (!connection.handshakeDone) {
      performHandshake(connection, data);
    } else {
      const message = decodeMessage(data);
      console.log('Message received:', message);
      broadcast(message, connection);
    }
  });

  connection.on('end', () => {
    console.log('Client disconnected');
    clients.delete(connection);
  });

  connection.on('error', (err) => {
    console.error('Connection error:', err);
    clients.delete(connection);
  });

  clients.add(connection);
});

wsServer.on('error', (error) => {
  console.error('Server error:', error);
});

wsServer.listen(3001, () => {
  console.log('WebSocket server listening on port 3001');
});

function performHandshake(connection, data) {
  const request = data.toString();
  const match = request.match(/Sec-WebSocket-Key: (.+)/);

  if (match) {
    const acceptKey = generateAcceptKey(match[1].trim());
    const response = [
      'HTTP/1.1 101 Switching Protocols',
      'Upgrade: websocket',
      'Connection: Upgrade',
      `Sec-WebSocket-Accept: ${acceptKey}`,
      '\r\n'
    ].join('\r\n');

    connection.write(response);
    connection.handshakeDone = true;
  }
}

function generateAcceptKey(key) {
  return crypto.createHash('sha1')
    .update(key + '258EAFA5-E914-47DA-95CA-C5AB0DC85B11')
    .digest('base64');
}

function decodeMessage(data) {
  const length = data[1] & 127;
  let maskStart = 2;
  let dataStart = maskStart + 4;

  const mask = data.slice(maskStart, maskStart + 4);
  const encoded = data.slice(dataStart, dataStart + length);
  
  let decoded = Buffer.alloc(length);
  for (let i = 0; i < length; i++) {
    decoded[i] = encoded[i] ^ mask[i % 4];
  }

  return decoded.toString('utf8');
}

function encodeMessage(message) {
  const payload = Buffer.from(message, 'utf8');
  const length = payload.length;
  const frame = Buffer.alloc(length + 2);
  frame[0] = 0x81;
  frame[1] = length;
  payload.copy(frame, 2);
  return frame;
}

function broadcast(message, sender) {
  const frame = encodeMessage(message);
  for (const client of clients) {
    if (client !== sender) {
      client.write(frame);
    }
  }
}
