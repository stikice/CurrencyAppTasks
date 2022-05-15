package com.example.currencyapp1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyapp1.model.RecyclerDataClass
import com.example.currencyapp1.ui.DataAdapter

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private var dataAdapter: DataAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        recyclerViewSetup()
    }

    fun recyclerViewSetup(){
        val randomList = listOf<RecyclerDataClass>(
            RecyclerDataClass.Currency("Тенге", "Казахстан",23450, R.drawable.flag_kz),
            RecyclerDataClass.Currency("Доллары", "США", 23450, R.drawable.flag_us),
            RecyclerDataClass.Currency("Лира", "Турция", 23450, R.drawable.flag_tk),
            RecyclerDataClass.Currency("Евро", "ЕС", 23450, R.drawable.flag_eu),
        )

        val currencyList = mutableListOf<RecyclerDataClass>(
            randomList[0],
            randomList[1],
            RecyclerDataClass.ActionButton("Добавить", R.drawable.plus_sign)
        )

        val currencyManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        val smoothScroller = object : LinearSmoothScroller(this) {
            override fun getVerticalSnapPreference(): Int = LinearSmoothScroller.SNAP_TO_START
        }

        dataAdapter = DataAdapter(
            onDeleteClickListener = {
                Log.d("cat", (it as? RecyclerDataClass.Currency)?.name.toString())
                currencyList.removeAt(5)
                dataAdapter?.setItems(currencyList)
            },
            onAddClickListener = {
                currencyList.add(currencyList.lastIndex, randomList.random())
                dataAdapter?.addItem(currencyList)
                smoothScroller.targetPosition = currencyList.size-1
                currencyManager.startSmoothScroll(smoothScroller) // плавная прокрутка
            }
        )

        recyclerView.apply {
            adapter = dataAdapter
            layoutManager = currencyManager
        }

        dataAdapter?.setItems(currencyList)


    }
}