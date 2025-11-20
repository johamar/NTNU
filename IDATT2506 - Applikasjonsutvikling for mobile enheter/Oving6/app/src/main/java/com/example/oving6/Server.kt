package com.example.oving6

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.ServerSocket
import java.net.Socket
import android.widget.TextView
import kotlinx.coroutines.*
import java.io.*

class Server(
    private val textViewReceived: TextView,
    private val textViewSent: TextView,
    private val port: Int = 12345
) {

    private lateinit var clientSocket: Socket
    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    fun start() {
        scope.launch {
            try {
                setUI("Starter tjener...")
                ServerSocket(port).use { serverSocket ->
                    setUI("Venter på klient...")

                    clientSocket = serverSocket.accept()
                    setUI("Klient koblet til: $clientSocket")

                    sendToClient("Velkommen! Du er koblet til serveren.")

                    listenForMessages()
                }
            } catch (e: Exception) {
                setUI("Feil: ${e.message}")
            }
        }
    }


    fun sendToClient(message: String) {
        scope.launch {
            try {
                if (!::clientSocket.isInitialized) {
                    setUI("Ingen klient tilkoblet")
                    return@launch
                }
                val writer = PrintWriter(BufferedWriter(OutputStreamWriter(clientSocket.getOutputStream())), true)
                writer.println(message)
                setSent(message)
            } catch (e: Exception) {
                setUI("Send-feil: ${e.message}")
            }
        }
    }

    private fun listenForMessages() {
        try {
            val reader = BufferedReader(InputStreamReader(clientSocket.getInputStream()))
            var message: String? = reader.readLine()
            while (message != null) {
                setReceived(message)
                message = reader.readLine()
            }
        } catch (e: Exception) {
            setUI("Lytte-feil: ${e.message}")
        }
    }

    private fun setUI(text: String) = MainScope().launch { textViewReceived.append("\n$text") }
    private fun setReceived(text: String) = MainScope().launch { textViewReceived.append("\n$text") }
    private fun setSent(text: String) = MainScope().launch { textViewSent.append("\n$text") }
}