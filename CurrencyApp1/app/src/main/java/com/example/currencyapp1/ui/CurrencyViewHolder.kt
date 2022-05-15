package com.example.currencyapp1.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyapp1.R
import com.example.currencyapp1.model.RecyclerDataClass
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class CurrencyViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_currency, parent, false)) {
    private val currencyTextView = itemView.findViewById<TextView>(R.id.currencyTextView)
    private val flagImageView = itemView.findViewById<ImageView>(R.id.flagImageView)
    private val currencyInputLayout = itemView.findViewById<TextInputLayout>(R.id.currencyInputLayout)
    private val currencyEditText = itemView.findViewById<TextInputEditText>(R.id.currencyEditText)

    fun bind(item: RecyclerDataClass, clickListener: (name: RecyclerDataClass) -> Unit) {
        item as RecyclerDataClass.Currency
        val titleText = itemView.context.getString(R.string.currency_title, item.name, item.country)
        currencyTextView.text = titleText
        flagImageView.setImageResource(item.imageRes)
        currencyInputLayout.hint = titleText
        currencyEditText.setText(item.count.toString())
    }
}