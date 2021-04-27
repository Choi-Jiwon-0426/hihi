package com.example.cal_recycler

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_33.*
import kotlinx.android.synthetic.main.test_dialog.*

class Fragment33 : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_33, container, false)
        val textView_test = view.findViewById<TextView>(R.id.textView_test)

        val str = arguments?.getString("test")
        textView_test.text = str




        return view
    }

}