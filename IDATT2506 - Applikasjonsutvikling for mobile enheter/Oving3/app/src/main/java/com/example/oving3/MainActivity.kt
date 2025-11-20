package com.example.oving3

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
class MainActivity : Activity() {
    private val names = ArrayList<String>()
    private val birthdays = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nameInput = findViewById<EditText>(R.id.inputName)
        val birthdayInput = findViewById<EditText>(R.id.inputBirthday)
        val addButton = findViewById<Button>(R.id.addButton)
        val showListButton = findViewById<Button>(R.id.showListButton)

        addButton.setOnClickListener {
            val name = nameInput.text.toString()
            val birthday = birthdayInput.text.toString()
            if (name.isNotEmpty() && birthday.isNotEmpty()) {
                names.add(name)
                birthdays.add(birthday)
                nameInput.text.clear()
                birthdayInput.text.clear()
            }
        }

        showListButton.setOnClickListener {
            val intent = Intent(this, ListActivity::class.java)
            intent.putStringArrayListExtra("names", names)
            intent.putStringArrayListExtra("birthdays", birthdays)
            startActivity(intent)
        }
    }
}