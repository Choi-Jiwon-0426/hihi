package com.example.cal_recycler


import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.calculator_item.view.*

class TestRecycler(private val items : MutableList<CalculatorData>, val context : Context)  :
        RecyclerView.Adapter<TestRecycler.ViewHolder>() {


    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("TAG","아이템개수, ${items.size}")
        Log.d("TAG","포지션, ${position}")
        val item = items[position]
        val listener = View.OnClickListener { it ->
            Toast.makeText(it.context, "Clicked: $item", Toast.LENGTH_SHORT).show()

            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val vv = inflater.inflate(R.layout.test_dialog, null)
            val button1 = vv.findViewById<Button>(R.id.button1)
            val result_dialog_text = vv.findViewById<TextView>(R.id.result_dialog_text)
            result_dialog_text.setText("${item.str_Num} = ${item.str_Result}")

            val alert = AlertDialog.Builder(context)
                    .create()
            alert.setView(vv)
            alert.show()
            button1.setOnClickListener {
                alert.dismiss();
            }

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
            Log.d("TAG","${item.str_Num}")
            view.rc_text2.text = item.str_Result
            Log.d("TAG","${item.str_Result}")
            view.setOnClickListener(listener)

        }
    }
}
