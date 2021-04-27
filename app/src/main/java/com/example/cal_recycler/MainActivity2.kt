package com.example.cal_recycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {

    var fragmentCal = Fragment11()
    var fragmentList = Fragment22()
    var fragmentTest = Fragment33()


    val fragment = R.id.fragmentArea

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)


        val bundle = Bundle()
        bundle.putString("test", "Hello World!!!!를 전달받았습니다.")
        fragmentTest.arguments = bundle


        button_goCal.setOnClickListener {
            val transaction = supportFragmentManager.beginTransaction()
            if (fragmentCal.isAdded) {
                transaction.replace(fragment, fragmentCal)
            } else {
                transaction.add(fragment, fragmentCal)
            }
            transaction.commit()
        }

        button_goList.setOnClickListener {
            val transaction = supportFragmentManager.beginTransaction()
            if (fragmentList.isAdded) {
                transaction.replace(fragment, fragmentList)
            } else {
                transaction.add(fragment, fragmentList)
            }
            transaction.commit()
        }

        button_goTest.setOnClickListener {
            val transaction = supportFragmentManager.beginTransaction()
            if (fragmentTest.isAdded) {
                transaction.replace(fragment, fragmentTest)
            } else {
                transaction.add(fragment, fragmentTest)
            }
            transaction.commit()
        }

    }


}