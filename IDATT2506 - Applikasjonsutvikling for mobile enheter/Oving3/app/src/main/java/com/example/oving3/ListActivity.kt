package com.example.oving3

import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView

class ListActivity : Activity() {
    private lateinit var names: ArrayList<String>
    private lateinit var birthdays: ArrayList<String>
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        names = intent.getStringArrayListExtra("names") ?: arrayListOf()
        birthdays = intent.getStringArrayListExtra("birthdays") ?: arrayListOf()

        val listView = findViewById<ListView>(R.id.friendsList)

        adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            makeDisplayList()
        )
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            val input = EditText(this)

            input.setText(names[position])

            AlertDialog.Builder(this)
                .setTitle("Endre venn")
                .setMessage("Oppdater venn")
                .setView(input)
                .setPositiveButton("Lagre") { _, _ ->
                    names[position] = input.text.toString()
                    refreshList()
                }
                .setNegativeButton("Avbryt", null)
                .show()
        }
    }

    private fun makeDisplayList(): List<String> =
        names.mapIndexed { i, n -> "$n (${birthdays[i]})" }
    private fun refreshList() {
        adapter.clear()
        adapter.addAll(makeDisplayList())
        adapter.notifyDataSetChanged()
    }
}
