package com.mobitant.seminar_5th_design

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.tapadoo.alerter.Alerter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_main.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //툴바의 종 모양을 클릭했을 때 이벤트
        //Alert가 위에서 나온다.
        notice.onClick {
            Alerter.create(this@MainActivity)
                .setTitle("알림 입니다.")
                .setText("닫기를 눌러 주세요")
                .addButton("닫기",R.style.AlertButton, View.OnClickListener {
                    Alerter.hide()
                })
                .setBackgroundColorInt(Color.parseColor("#e74c3c"))
                .setIcon(R.drawable.icn_ios_notice)
                .show()
        }

        //학사일정 보러가기를 눌렀을 때 이벤트
        // CalenderActivity로 넘어감
        calender.onClick{
            startActivity<CalenderActivity>()
        }

        //news 의 문자열이 흐르게 하려면 text가 선택되어 있어야 한다.
        txt_main_news.isSelected = true

        //아래의 8개의 대시보드를 누르면 아이콘의 색상이 변한다.
        //다시 한번 더 누르면 하얀색으로 돌아온다.
        img_bubble.onClick { img_bubble.switchState() }
        img_community.onClick { img_community.switchState() }
        img_folio.onClick { img_folio.switchState() }
        img_info.onClick { img_info.switchState() }
        img_mail.onClick { img_mail.switchState() }
        img_news.onClick { img_news.switchState() }
        img_phone.onClick { img_phone.switchState() }
        img_library.onClick {img_library.switchState()}
    }
}
