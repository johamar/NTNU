package com.example.oving2

import android.app.Activity
import android.app.ComponentCaller
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlin.text.toIntOrNull

class MathActivity : Activity() {

    private lateinit var number1: TextView
    private lateinit var number2: TextView
    private lateinit var editSvar: EditText
    private lateinit var editOvreGrense: EditText
    private lateinit var addButton: Button
    private lateinit var multButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_math)

        number1 = findViewById(R.id.number1)
        number2 = findViewById(R.id.number2)
        editSvar = findViewById(R.id.editSvar)
        editOvreGrense = findViewById(R.id.editOvreGrense)
        addButton = findViewById(R.id.buttonAdd)
        multButton = findViewById(R.id.buttonMult)
    }

    fun onClickAdd(v: View?) {
        val n1 = number1.text.toString().toInt()
        val n2 = number2.text.toString().toInt()
        val userAnswer = editSvar.text.toString().toInt()

        val correct = n1 + n2
        if (userAnswer == correct) {
            Toast.makeText(this,
                getString(R.string.riktig),
                Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this,
                "${getString(R.string.feil)} $correct",
                Toast.LENGTH_SHORT).show()
        }
        setNewNumbers()
    }

    fun onClickMultiply(v: View?) {
        val n1 = number1.text.toString().toInt()
        val n2 = number2.text.toString().toInt()
        val userAnswer = editSvar.text.toString().toInt()

        val correct = n1 * n2
        if (userAnswer == correct) {
            Toast.makeText(this,
                getString(R.string.riktig),
                Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this,
                "${getString(R.string.feil)} $correct",
                Toast.LENGTH_SHORT).show()
        }
        setNewNumbers()
    }

    private fun setNewNumbers() {
        val upperLimit = editOvreGrense.text.toString().toIntOrNull() ?: 10

        val intent1 = Intent(this, RandomNumberActivity::class.java)
        intent1.putExtra("upperLimit", upperLimit)
        startActivityForResult(intent1, 1)

        val intent2 = Intent(this, RandomNumberActivity::class.java)
        intent2.putExtra("upperLimit", upperLimit)
        startActivityForResult(intent2, 2)
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?,
    ) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK && data != null) {
            val value = data.getIntExtra("randomValue", -1)
            when (requestCode) {
                1 -> number1.text = value.toString()
                2 -> number2.text = value.toString()
            }
        }
    }

}