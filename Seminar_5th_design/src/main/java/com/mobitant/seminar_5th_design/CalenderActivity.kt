package com.mobitant.seminar_5th_design

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.toolbar_calender.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import java.text.SimpleDateFormat
import java.util.*

class CalenderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calender)

        //현재 날짜 불러오기
        val sdf = SimpleDateFormat("yyyy년 M월 dd일 ")
        val currentDate = sdf.format(Date())
        date.text = currentDate

        back.onClick {
            finish()
        }
    }
}
