package com.example.cal_recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView



class TestRecyclerTextList (private val items : MutableList<String>, val context : Context)  :
    RecyclerView.Adapter<TestRecyclerTextList.ViewHolder>() {

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.apply {
            bind(item)
            itemView.tag = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.textadd_item, parent, false)
        //가져와서 초기화 할게 있으면.. create가 1번이니까..여기다 하면됨..
        return ViewHolder(inflatedView)
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View = v
        fun bind(item : String) {
            view.findViewById<TextView>(R.id.textView_textList).text = item
        }

    }


    }



