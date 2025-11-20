package com.example.oving2

import android.app.Activity
import android.content.Intent
import android.os.Bundle

class RandomNumberActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val upperLimit = intent.getIntExtra("upperLimit", 100)
        val value = (0..upperLimit).random()

        //Toast.makeText(this, "tilfeldig tall: $value", Toast.LENGTH_LONG).show()

        val resultIntent = Intent()
        resultIntent.putExtra("randomValue", value)
        setResult(RESULT_OK, resultIntent)

        finish()
    }

}