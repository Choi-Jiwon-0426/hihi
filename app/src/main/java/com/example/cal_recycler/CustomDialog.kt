package com.example.cal_recycler

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatDialog
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.calculator_item.*
import kotlinx.android.synthetic.main.dialog_list.*
import kotlinx.android.synthetic.main.test_dialog.*

class CustomDialog(val ctx: Context, val dataList : MutableList<CalculatorData>) : AppCompatDialog(ctx) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_list)

        if (window != null) {
            window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.MATCH_PARENT)
        }


        val testRecyclerList = TestRecyclerList(dataList, ctx)

        recycler_view2.apply {
            layoutManager = LinearLayoutManager(
                    context,
                    LinearLayoutManager.VERTICAL,
                    false)
            adapter = testRecyclerList
        }
        // 어댑터 아이템이 변경되면 반드시 호출해야 함
        testRecyclerList.notifyDataSetChanged()

        button_close.setOnClickListener {
            this.dismiss()
        }



    }




}