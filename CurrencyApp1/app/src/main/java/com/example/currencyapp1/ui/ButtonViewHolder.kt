package com.example.currencyapp1.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyapp1.R
import com.example.currencyapp1.model.RecyclerDataClass
import com.google.android.material.button.MaterialButton

class ButtonViewHolder (inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_button, parent, false)) {
    private val button = itemView.findViewById<MaterialButton>(R.id.button)

    fun bind(item: RecyclerDataClass, clickListener: (name: RecyclerDataClass) -> Unit) {
        item as RecyclerDataClass.ActionButton
        button.text = item.actionName
        button.setIconResource(item.imageRes)

        button.setOnClickListener {
            clickListener(item)
        }
    }
}