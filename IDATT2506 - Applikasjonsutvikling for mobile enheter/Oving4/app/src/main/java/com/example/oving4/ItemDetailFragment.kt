package com.example.oving4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class ItemDetailFragment : Fragment() {

    private var currentIndex = 0
    private lateinit var items: List<Player>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        items = (activity as MainActivity).manUnited2008
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_item_detail, container, false)
    }

    fun showItem(index: Int) {
        currentIndex = index
        val item = items[index]
        view?.findViewById<ImageView>(R.id.imageView)?.setImageResource(item.imageResId)
        view?.findViewById<TextView>(R.id.description)?.text = item.description
    }

    fun showNext() {
        if (currentIndex < items.size - 1) showItem(currentIndex + 1)
    }

    fun showPrevious() {
        if (currentIndex > 0) showItem(currentIndex - 1)
    }
}