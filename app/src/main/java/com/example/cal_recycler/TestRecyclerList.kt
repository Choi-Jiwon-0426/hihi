package com.example.cal_recycler


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.calculator_item.view.*

class TestRecyclerList(private val items : MutableList<CalculatorData>, val context : Context)  :
        RecyclerView.Adapter<TestRecyclerList.ViewHolder>() {


    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        val listener = View.OnClickListener { it ->
            Toast.makeText(it.context, "Clicked: $item", Toast.LENGTH_SHORT).show()
        }
        holder.apply {
            bind(listener, item)
            itemView.tag = item
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.calculator_item, parent, false)
        //가져와서 초기화 할게 있으면.. create가 1번이니까..여기다 하면됨..
        return ViewHolder(inflatedView)
    }


    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View = v
        fun bind(listener: View.OnClickListener, item: CalculatorData) {
            view.rc_textDate.text = item.str_Date
            view.rc_text1.text = item.str_Num
            view.rc_text2.text = item.str_Result
            view.setOnClickListener(listener)

        }
    }
}
