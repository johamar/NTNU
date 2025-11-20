package com.example.oving6

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.Socket
import android.widget.TextView
import kotlinx.coroutines.*
import java.io.*
import java.net.ServerSocket

class Client(
    private val textViewReceived: TextView,
    private val textViewSent: TextView,
    private val serverIP: String = "10.0.2.2",
    private val serverPort: Int = 12345
) {

    private lateinit var socket: Socket
    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    fun start() {
        scope.launch {
            try {
                setUI("Kobler til server...")
                socket = Socket(serverIP, serverPort)
                setUI("Koblet til server: $socket")
                listenForMessages(socket)
            } catch (e: Exception) {
                setUI("Feil: ${e.message}")
            }
        }
    }

    fun sendToServer(message: String) {
        scope.launch {
            try {
                val writer = PrintWriter(BufferedWriter(OutputStreamWriter(socket.getOutputStream())), true)
                writer.println(message)
                setSent(message)
            } catch (e: Exception) {
                setUI("Send-feil: ${e.message}")
            }
        }
    }

    private fun listenForMessages(socket: Socket) {
        try {
            val reader = BufferedReader(InputStreamReader(socket.getInputStream()))
            var message: String? = reader.readLine()
            while (message != null) {
                setReceived(message)
                message = reader.readLine()
            }
        } catch (e: Exception) {
            setUI("Motta-feil: ${e.message}")
        }
    }

    private fun setUI(text: String) = MainScope().launch { textViewReceived.append("\n$text") }
    private fun setSent(text: String) = MainScope().launch { textViewSent.append("\n$text") }
    private fun setReceived(text: String) = MainScope().launch { textViewReceived.append("\n$text") }
}