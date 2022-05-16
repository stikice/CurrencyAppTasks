package com.example.currencyapp1.ui

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyapp1.R
import com.example.currencyapp1.model.RecyclerDataClass

class DataAdapter(
    private val onAddClickListener: () -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private val data = mutableListOf<RecyclerDataClass>()

    fun moveItem(from: Int, to: Int) {
        val fromData = data[from]
        data.removeAt(from)
        if (to < from) {
            data.add(to, fromData)
        } else {
            data.add(to - 1, fromData)
        }
    }

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
            is CurrencyViewHolder -> holder.bind(data[position])
            is ButtonViewHolder -> holder.bind(data[position], onAddClickListener)
        }
    }

    fun setItems(list: List<RecyclerDataClass>){
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

    fun addItem(list: List<RecyclerDataClass>){
        val prevSize = list.size
        data.clear()
        data.addAll(list)
        notifyItemRangeInserted(prevSize-1, data.size)
    }
    fun removeItem(list: List<RecyclerDataClass>, position: Int){
        data.clear()
        data.addAll(list)
        notifyItemRemoved(position)
    }


}