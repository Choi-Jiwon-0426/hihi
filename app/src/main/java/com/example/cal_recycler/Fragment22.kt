package com.example.cal_recycler

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*


class Fragment22 : Fragment() {

    lateinit var ctx : Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_22, container, false)

        ctx = view.context

        val edt = view.findViewById<EditText>(R.id.edt)
        val btn_add = view.findViewById<Button>(R.id.button_textAdd)
        val fmt2_recyler = view.findViewById<RecyclerView>(R.id.fmt2_recyler)

        var str_data = mutableListOf<String>()

        btn_add.setOnClickListener {
            str_data.add(0, "${edt.text}")
            edt.setText("")
            Log.d("TAG","${str_data}")

            val testRecyclerTextList = TestRecyclerTextList(str_data, ctx)

            fmt2_recyler.apply {
                layoutManager = LinearLayoutManager(
                    ctx,
                    LinearLayoutManager.VERTICAL,
                    false)
                adapter = testRecyclerTextList
            }
            testRecyclerTextList.notifyDataSetChanged()
        }

        return view
    }


}