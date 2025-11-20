package com.example.oving2

import android.app.Activity
import android.app.ComponentCaller
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
class MainActivity : Activity() {
    private val randomRequestCode: Int = 1
    private var lastRandomValue: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickStartRandomNumberActivity(v: View?) {
        val intent = Intent(this, RandomNumberActivity::class.java)
        intent.putExtra("upperLimit", 200)
        startActivityForResult(intent, randomRequestCode)
    }

    public override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent,
    ) {
        if (resultCode != RESULT_OK) {
            Log.e("onActivityResult()", "Noe gikk galt")
            return
        }
        if (requestCode == randomRequestCode) {
            lastRandomValue = data.getIntExtra("randomValue", -1)
            val textView = findViewById<View>(R.id.textView) as TextView
            textView.text = "Tilfeldig tall: $lastRandomValue"
        }
    }
}