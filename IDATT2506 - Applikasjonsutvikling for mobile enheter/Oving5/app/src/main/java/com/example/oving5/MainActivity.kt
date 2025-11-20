package com.example.oving5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.*

class MainActivity : AppCompatActivity() {

    private val client = OkHttpClient.Builder()
        .cookieJar(object : CookieJar {
            private val cookieStore = mutableMapOf<String, List<Cookie>>()

            override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
                cookieStore[url.host] = cookies
            }

            override fun loadForRequest(url: HttpUrl): List<Cookie> {
                return cookieStore[url.host] ?: listOf()
            }
        }).build()

    private val url = "https://bigdata.idi.ntnu.no/mobil/tallspill.jsp"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nameInput = findViewById<EditText>(R.id.nameInput)
        val cardInput = findViewById<EditText>(R.id.cardInput)
        val guessInput = findViewById<EditText>(R.id.guessInput)
        val resultView = findViewById<TextView>(R.id.resultView)
        val startButton = findViewById<Button>(R.id.startButton)
        val guessButton = findViewById<Button>(R.id.guessButton)

        startButton.setOnClickListener {
            val navn = nameInput.text.toString()
            val kort = cardInput.text.toString()
            sendRequest("navn" to navn, "kortnummer" to kort) { response ->
                resultView.text = response
            }
        }

        guessButton.setOnClickListener {
            val tall = guessInput.text.toString()
            sendRequest("tall" to tall) { response ->
                resultView.text = response
            }
        }
    }

    private fun sendRequest(vararg params: Pair<String, String>, callback: (String) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val formBody = FormBody.Builder().apply {
                for ((key, value) in params) {
                    add(key, value)
                }
            }.build()

            val request = Request.Builder()
                .url(url)
                .post(formBody)
                .build()

            try {
                val response = client.newCall(request).execute()
                val responseText = response.body?.string() ?: "Ingen respons"
                Log.d("Tallspill", "Response: $responseText")

                withContext(Dispatchers.Main) { callback(responseText) }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) { callback("Feil: ${e.message}") }
            }
        }
    }
}