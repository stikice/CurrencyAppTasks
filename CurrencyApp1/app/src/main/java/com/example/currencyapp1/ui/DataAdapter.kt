package com.example.currencyapp1.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyapp1.R
import com.example.currencyapp1.model.RecyclerDataClass

class DataAdapter(
    private val clickListener: (name: RecyclerDataClass) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private val data = mutableListOf<RecyclerDataClass>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when(viewType){
            R.layout.item_currency -> CurrencyViewHolder(inflater, parent)
            R.layout.item_button -> ButtonViewHolder(inflater, parent)
            else -> CurrencyViewHolder(inflater, parent)
        }
    }

    override fun getItemViewType(position: Int): Int =
        when(data[position]){
            is RecyclerDataClass.Currency -> R.layout.item_currency
            is RecyclerDataClass.ActionButton -> R.layout.item_button
            else -> R.layout.item_currency
        }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CurrencyViewHolder -> holder.bind(data[position], clickListener)
            is ButtonViewHolder -> holder.bind(data[position], clickListener)
        }
    }

    fun setItems(list: List<RecyclerDataClass>){
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }


}