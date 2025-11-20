package com.example.oving6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class ClientActivity : AppCompatActivity() {

    private lateinit var client: Client

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val input = findViewById<EditText>(R.id.messageInput)
        val sendButton = findViewById<Button>(R.id.sendButton)
        val received = findViewById<TextView>(R.id.receivedMessagesText)
        val sent = findViewById<TextView>(R.id.sentMessagesText)

        client = Client(received, sent)
        client.start()

        sendButton.setOnClickListener {
            val msg = input.text.toString()
            client.sendToServer(msg)
            input.text.clear()
        }
    }
}