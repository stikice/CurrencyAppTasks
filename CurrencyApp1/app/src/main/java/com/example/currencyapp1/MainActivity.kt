package com.example.currencyapp1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyapp1.model.RecyclerDataClass
import com.example.currencyapp1.ui.DataAdapter

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dataAdapter = DataAdapter(
            clickListener = {
                Log.d("cat name:", "ad")
            }
        )

        val currencyManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.apply {
            adapter = dataAdapter
            layoutManager = currencyManager
        }

        val currencyList = listOf<RecyclerDataClass>(
            RecyclerDataClass.Currency("Тенге", "Казахстан",23450, R.drawable.flag_kz),
            RecyclerDataClass.Currency("Доллары", "США", 23450, R.drawable.flag_us),
            RecyclerDataClass.ActionButton("Добавить", R.drawable.plus_sign)
        )

        dataAdapter.setItems(currencyList)
    }
}