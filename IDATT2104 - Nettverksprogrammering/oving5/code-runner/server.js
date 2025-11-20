const express = require("express");
const cors = require("cors");
const bodyParser = require("body-parser");
const { exec } = require("child_process");
const fs = require("fs");

const app = express();
app.use(cors());
app.use(bodyParser.json());

// Start en persistent container når serveren starter
exec("docker run -dit --name cpp-runner -v $(pwd):/app gcc:latest bash", (err) => {
    if (err) console.error("Kunne ikke starte container:", err);
});

app.post("/run", (req, res) => {
    const { code } = req.body;
    fs.writeFileSync("main.cpp", code); // Lagre ny kode

    // Kjør koden i den eksisterende containeren
    exec("docker exec cpp-runner bash -c 'g++ /app/main.cpp -o /app/main && /app/main'", (error, stdout, stderr) => {
        res.json({ output: error ? stderr : stdout });
    });
});

app.listen(3000, () => console.log("Server running on port 3000"));
