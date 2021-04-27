package com.example.cal_recycler

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    var calcType = 0
    var calcResult = 0.0
    val data : MutableList<CalculatorData> = mutableListOf()
    val calendar = Calendar.getInstance() //정말 많이씀.
    val sdf = SimpleDateFormat("yyyy-MM-dd HH-mm", Locale.getDefault())



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_1.setOnClickListener(onClickListener)
        button_2.setOnClickListener(onClickListener)
        button_3.setOnClickListener(onClickListener)
        button_4.setOnClickListener(onClickListener)
        button_5.setOnClickListener(onClickListener)
        button_6.setOnClickListener(onClickListener)
        button_7.setOnClickListener(onClickListener)
        button_8.setOnClickListener(onClickListener)
        button_9.setOnClickListener(onClickListener)
        button_0.setOnClickListener(onClickListener)

        button_Sub.setOnClickListener(onClickListener)
        button_Plus.setOnClickListener(onClickListener)
        button_Div.setOnClickListener(onClickListener)
        button_Mul.setOnClickListener(onClickListener)
        button_Result.setOnClickListener(onClickListener)
        button_Dot.setOnClickListener(onClickListener)
        button_Del.setOnClickListener(onClickListener)
        button_ResultList.setOnClickListener(onClickListener)
    }

    //리스너의 오브젝트(인자) 화?
    private val onClickListener = View.OnClickListener {

        when (it.id) {
            R.id.button_1 -> {
                text_Num.setText(text_Num.getText().append("1"))
            }
            R.id.button_2 -> {
                text_Num.setText(text_Num.getText().append("2"))
            }
            R.id.button_3 -> {
                text_Num.setText(text_Num.getText().append("3"))
            }
            R.id.button_4 -> {
                text_Num.setText(text_Num.getText().append("4"))
            }
            R.id.button_5 -> {
                text_Num.setText(text_Num.getText().append("5"))
            }
            R.id.button_6 -> {
                text_Num.setText(text_Num.getText().append("6"))
            }
            R.id.button_7 -> {
                text_Num.setText(text_Num.getText().append("7"))
            }
            R.id.button_8 -> {
                text_Num.setText(text_Num.getText().append("8"))
            }
            R.id.button_9 -> {
                text_Num.setText(text_Num.getText().append("9"))
            }
            R.id.button_0 -> {
                text_Num.setText(text_Num.getText().append("0"))
            }
            R.id.button_Dot -> {
                if (!text_Num.text.toString().contains(".")) {
                    //text_num.text = text_num.text.toString() + "."
                    text_Num.setText(text_Num.getText().append("."))
                }
            }
            R.id.button_Sub -> {
                calc()

                calcType = 1
                if (calcResult == 0.0) {
                    calcResult = if (text_Num.text.isEmpty()) 0.0 else text_Num.text.toString().toDouble()
                }

                text_History.setText(text_History.text.toString() + text_Num.text.toString() + "-")
                text_Num.setText("")
            }
            R.id.button_Plus -> {
                calc()
                calcType = 2

                if (calcResult == 0.0) {
                    calcResult = if (text_Num.text.isEmpty()) 0.0 else text_Num.text.toString().toDouble()
                }

                text_History.setText(text_History.text.toString() + text_Num.text.toString() + "+")
                text_Num.setText("")
            }
            R.id.button_Div -> {
                calc()
                calcType = 3

                if (calcResult == 0.0) {
                    calcResult = if (text_Num.text.isEmpty()) 0.0 else text_Num.text.toString().toDouble()
                }

                text_History.setText(text_History.text.toString() + text_Num.text.toString() + "/")
                text_Num.setText("")
            }
            R.id.button_Mul -> {
                calc()
                calcType = 4

                if (calcResult == 0.0) {
                    calcResult = if (text_Num.text.isEmpty()) 0.0 else text_Num.text.toString().toDouble()
                }

                text_History.setText(text_History.text.toString() + text_Num.text.toString() + "*")
                text_Num.setText("")
            }
            R.id.button_Result -> {
                if (calcType != 0) {
                    val value =
                        if (text_Num.text.isEmpty()) 0.0 else text_Num.text.toString().toDouble()

                    calc()
                    text_Num.setText(calcResult.toString())
                    text_History.setText(text_History.text.toString() + value)
                }

                calcType = 0

                dialog_function()

            }// R.id.button_Result end

            R.id.button_ResultList -> {
                val customDialog = CustomDialog(this, data)
                customDialog.show()
            }

            R.id.button_Del -> {
                text_History.setText("")
                text_Num.setText("")
                calcType = 0
                calcResult = 0.0
            }
        }//온클릭리스너 등록
    }

    private fun calc() {
        val value = if (text_Num.text.isEmpty()) 0.0 else text_Num.text.toString().toDouble()

        when (calcType) {
            1 -> calcResult = minus(calcResult, value).toDouble()
            2 -> calcResult = plus(calcResult, value).toDouble()
            3 -> {
                if (calcResult == 0.0) {
                    calcResult = value
                    return
                }

                if (text_History.text.isNotEmpty() && value == 0.0) {
                    Toast.makeText(this, "0으로 나눌 수 없습니다.", Toast.LENGTH_SHORT).show()
                    Log.d("AAAAAA", "---> ${text_History.text} $value, $calcResult")
                    calcResult = 0.0
                    return
                }

                Log.d("AAAAAA", "---> ${text_History.text} $value, $calcResult")
                calcResult = div(calcResult, value).toDouble()
            }
            4 -> {
                if (text_History.text.isEmpty() && calcResult == 0.0) {
                    calcResult = value
                    return
                }
                calcResult = multi(calcResult, value).toDouble()
                Log.d("AAAAAA", "---> ${text_History.text} $value, $calcResult")
            }
        }

        Log.d("AAAAAA", "---> $value, $calcResult")
    } //cal function finish

    fun plus(p1: Double, p2: Double): BigDecimal =
        BigDecimal.valueOf(p1).add(BigDecimal.valueOf(p2))

    fun minus(p1: Double, p2: Double): BigDecimal =
            BigDecimal.valueOf(p1).subtract(BigDecimal.valueOf(p2))

    fun div(p1: Double, p2: Double): BigDecimal =
            BigDecimal.valueOf(p1).divide(BigDecimal.valueOf(p2))

    fun multi(p1: Double, p2: Double): BigDecimal =
            BigDecimal.valueOf(p1).multiply(BigDecimal.valueOf(p2))

    fun dialog_function(){
        //알럿
        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val vv = inflater.inflate(R.layout.test_dialog, null)
        val button1 = vv.findViewById<Button>(R.id.button1)
        val result_dialog_text = vv.findViewById<TextView>(R.id.result_dialog_text)
        result_dialog_text.setText("${text_History.text.toString()} = ${calcResult.toString()}")

        val alert = AlertDialog.Builder(this)
            .create()
        alert.setView(vv)
        alert.show()

        button1.setOnClickListener {
            /*val calendar = Calendar.getInstance() //정말 많이씀.
            val sdf = SimpleDateFormat("yyyy-MM-dd HH-mm", Locale.getDefault())*/

            val memo= CalculatorData(sdf.format(calendar.time),text_History.text.toString(), calcResult.toString())
            data.add(memo)
            Log.d("TAG","${data}")

            //TestRecycler 클래스
            val testAdapter = TestRecycler(data, this)

            //뷰의 리사이클러 뷰 id에 적용! 하단 코드 공통.(리사이클러 클래스에 적용해도 됨?)
            recycler_view.apply {
                layoutManager = LinearLayoutManager(
                    context,
                    LinearLayoutManager.VERTICAL,
                    false)
                adapter = testAdapter
            }
            // 어댑터 아이템이 변경되면 반드시 호출해야 함
            testAdapter.notifyDataSetChanged()

            text_History.setText("")
            text_Num.setText("")
            calcType = 0
            calcResult = 0.0

            alert.dismiss();
        }

    }//다이얼로그







}