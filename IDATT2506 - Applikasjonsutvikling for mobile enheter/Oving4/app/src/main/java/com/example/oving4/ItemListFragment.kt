package com.example.oving4

import android.R
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.fragment.app.ListFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ItemListFragment : ListFragment() {

    private var listener: OnItemSelectedListener? = null
    private lateinit var items: List<Player>

    interface OnItemSelectedListener {
        fun onItemSelected(position: Int)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? OnItemSelectedListener
            ?: throw ClassCastException("$context must implement OnItemSelectedListener")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        items = (activity as MainActivity).manUnited2008
        listAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, items.map { it.name })
    }

    override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {
        listener?.onItemSelected(position)
    }
}