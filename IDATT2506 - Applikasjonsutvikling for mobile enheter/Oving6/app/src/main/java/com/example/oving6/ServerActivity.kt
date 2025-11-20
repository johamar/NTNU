package com.example.oving6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*

class ServerActivity : AppCompatActivity() {

    private lateinit var server: Server

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val input = findViewById<EditText>(R.id.messageInput)
        val sendButton = findViewById<Button>(R.id.sendButton)
        val received = findViewById<TextView>(R.id.receivedMessagesText)
        val sent = findViewById<TextView>(R.id.sentMessagesText)

        server = Server(received, sent)
        server.start()

        sendButton.setOnClickListener {
            val msg = input.text.toString()
            server.sendToClient(msg)
            input.text.clear()
        }
    }
}