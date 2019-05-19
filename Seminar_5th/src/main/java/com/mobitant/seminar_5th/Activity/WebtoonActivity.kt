package com.mobitant.seminar_5th.Activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mobitant.seminar_5th.R
import kotlinx.android.synthetic.main.toolbar_webtoon.*
import org.jetbrains.anko.startActivity

class WebtoonActivity : AppCompatActivity() {

    lateinit var title: String
    var product_id = -1
    var episode_id = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webtoon)

        configureTitleBar()
    }

    //웹툰 내용으로부터 웹툰 제목과 제품id, 에피소드id를 가져와
    //웹툰 제목은 타이틀바에 표시하고
    //제품id와 에피소드id는 디폴트 값인경우 화면이 사라진다.
    private fun configureTitleBar(){
        title = intent.getStringExtra("title")
        product_id = intent.getIntExtra("idx", -1)
        episode_id = intent.getIntExtra("episode_id", -1)
        if(product_id == -1 || episode_id == -1) {finish()}

        txt_toolbar_webtoon_title.text = title

        //댓글 달기 화면으로 이동
        btn_toolbar_webtoon_comment.setOnClickListener {
            startActivity<CommentActivity>()
        }
        btn_toolbar_webtoon_back.setOnClickListener {
            finish()
        }
    }
}
