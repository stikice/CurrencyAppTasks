package com.example.currencyapp1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.*
import androidx.recyclerview.widget.ItemTouchHelper.*
import com.example.currencyapp1.model.RecyclerDataClass
import com.example.currencyapp1.ui.ButtonViewHolder
import com.example.currencyapp1.ui.DataAdapter

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private var dataAdapter: DataAdapter? = null
    private val randomList = listOf<RecyclerDataClass>(
        RecyclerDataClass.Currency("Тенге", "Казахстан",23450, R.drawable.flag_kz),
        RecyclerDataClass.Currency("Доллары", "США", 23450, R.drawable.flag_us),
        RecyclerDataClass.Currency("Лира", "Турция", 23450, R.drawable.flag_tk),
        RecyclerDataClass.Currency("Евро", "ЕС", 23450, R.drawable.flag_eu),
    )
    private val currencyList = mutableListOf<RecyclerDataClass>(
        randomList[0],
        randomList[1],
        RecyclerDataClass.ActionButton("Добавить", R.drawable.plus_sign)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        recyclerViewSetup()
    }

    fun recyclerViewSetup(){
        val currencyManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        val smoothScroller = object : LinearSmoothScroller(this) {
            override fun getVerticalSnapPreference(): Int = LinearSmoothScroller.SNAP_TO_START
        }

        dataAdapter = DataAdapter(
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

        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private val itemTouchHelper by lazy {
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(UP or DOWN, RIGHT or LEFT){
            override fun onMove(recyclerView: RecyclerView,
                                viewHolder: RecyclerView.ViewHolder,
                                target: RecyclerView.ViewHolder): Boolean {
                val adapter = recyclerView.adapter as DataAdapter
                val from = viewHolder.adapterPosition
                val to = target.adapterPosition
                adapter.moveItem(from, to)
                adapter.notifyItemMoved(from, to)

                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    currencyList.removeAt(viewHolder.adapterPosition)
                    dataAdapter?.removeItem(currencyList, viewHolder.adapterPosition)
            }

            override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
                super.onSelectedChanged(viewHolder, actionState)

                if (actionState == ACTION_STATE_DRAG) {
                    viewHolder?.itemView?.alpha = 0.5f
                }
            }

            override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
                super.clearView(recyclerView, viewHolder)

                viewHolder.itemView.alpha = 1.0f
            }

            override fun getSwipeDirs(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                if(viewHolder is ButtonViewHolder) return 0
                return super.getSwipeDirs(recyclerView, viewHolder)
            }

        })
    }
}