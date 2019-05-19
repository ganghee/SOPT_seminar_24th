package com.mobitant.seminar_5th.Activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.mobitant.seminar_5th.Adapter.CommentRecyclerViewAdapter
import com.mobitant.seminar_5th.Data.CommentData
import com.mobitant.seminar_5th.R
import kotlinx.android.synthetic.main.activity_comment.*
import kotlinx.android.synthetic.main.toolbar_comment.*

class CommentActivity : AppCompatActivity() {

    lateinit var commentRecyclerViewAdapter: CommentRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment)

        configureTitleBar()
        configureRecyclerView()
    }

    //뒤로가기 이미지를 누르면 화면이 없어짐
    private fun configureTitleBar(){
        btn_toolbar_comment_back.setOnClickListener {
            finish()
        }
    }
    //댓글 데이터를 배열에 넣기
    private fun configureRecyclerView(){
        var dataList: ArrayList<CommentData> = ArrayList()
        dataList.add(CommentData(0,0,0,
            "http://sopt.org/wp/wp-content/uploads/2014/01/24_SOPT-LOGO_COLOR-1.png", "솝러버",
            "문어에 대한 내용이 아주 유익하네요. 추천드룡! 다들 꼭 보시길~^^", "19.03.25 23:21:38"))
        dataList.add(CommentData(0,0,0,
            "http://sopt.org/wp/wp-content/uploads/2014/01/24_SOPT-LOGO_COLOR-1.png", "솝맘",
            "타코야끼가 생각나는 웹툰이에요 :) 타코야끼 먹으면서 읽는 거 추천~!", "19.03.25 23:25:38"))
        dataList.add(CommentData(0,0,0,
            "http://sopt.org/wp/wp-content/uploads/2014/01/24_SOPT-LOGO_COLOR-1.png", "조총무",
            "심심할 때 할 게 없다면 이 웹툰을 읽어보세여!! _ 맑고 개끗한 조총무", "19.03.25 23:25:38"))
        dataList.add(
            CommentData(0,0,0,
            "http://sopt.org/wp/wp-content/uploads/2014/01/24_SOPT-LOGO_COLOR-1.png", "김스윗",
            "감동적인 이야기 입니다,,, 아주 스윗한 ㅇ웹툰이네요ㅠㅋㅋㅋㅋㅋ", "19.03.25 23:25:38")
        )

        txt_toolbar_comment_title.text = "댓글(" + dataList.size.toString() + ")"

        //세로로 배열
        commentRecyclerViewAdapter = CommentRecyclerViewAdapter(this, dataList)
        rv_comment_list.adapter = commentRecyclerViewAdapter
        rv_comment_list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        //경계선 넣기
        rv_comment_list.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }
}
