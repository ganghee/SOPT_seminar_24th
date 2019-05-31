package com.mobitant.seminar_6th.Activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.mobitant.seminar_6th.Adapter.CommentRecyclerViewAdapter
import com.mobitant.seminar_6th.Data.CommentData
import com.mobitant.seminar_6th.Network.ApplicationController
import com.mobitant.seminar_6th.Network.Get.GetCommentReadResponse
import com.mobitant.seminar_6th.Network.NetworkService
import com.mobitant.seminar_6th.R
import kotlinx.android.synthetic.main.activity_comment.*
import kotlinx.android.synthetic.main.toolbar_comment.*
import org.jetbrains.anko.startActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentActivity : AppCompatActivity() {

    lateinit var commentRecyclerViewAdapter: CommentRecyclerViewAdapter
    var idx:Int = -1
    var chapter: Int = -1
    val networkService: NetworkService by lazy{
        ApplicationController.instance.networkService
    }
    var cmtIdx : Int = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment)

        configureTitleBar()
        configureRecyclerView()


        btn_write_comment.setOnClickListener {
            startActivity<CommentWriteActivity>(
                "idx" to idx,
                "chapter" to chapter
            )
        }
    }

    override fun onResume() {
        super.onResume()
        configureRecyclerView()
        getCommentReadResponse()
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

        txt_toolbar_comment_title.text = "댓글(" + dataList.size.toString() + ")"

        //세로로 배열
        commentRecyclerViewAdapter = CommentRecyclerViewAdapter(this, dataList)
        rv_comment.adapter = commentRecyclerViewAdapter
        rv_comment.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        //경계선 넣기
        //rv_comment.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        getCommentReadResponse()


    }


    private fun getCommentReadResponse() {

        //intent에서 넘어온 정보는 onCreate에서 작성하면 디폴트 값이 저장된다.
        //따라서 메소드를 따로 만든다음 그 안에 intent객체를 구현한다.
        idx = intent.getIntExtra("idx", -1)
        chapter = intent.getIntExtra("chapter", -1)
        if(idx == -1 || chapter == -1) {finish()}

        Log.d("@@@","@@@@@@  idx  @@@@@@@@"+idx+"######  chapter  ####"+chapter)
        val getCommentReadResponse = networkService.getCommentReadResponse(
            "application/json", idx)

        getCommentReadResponse.enqueue(object : Callback<GetCommentReadResponse> {
            override fun onFailure(call: Call<GetCommentReadResponse>, t: Throwable) {
                Log.e("CommentRead Fail", t.toString())
            }

            override fun onResponse(
                call: Call<GetCommentReadResponse>,
                response: Response<GetCommentReadResponse>
            ) {
                if (response.isSuccessful) {
                    if (response.body()!!.status == 200) {
                        val tmp= response.body()!!.data!!
                        commentRecyclerViewAdapter.dataList = tmp
                        commentRecyclerViewAdapter.notifyDataSetChanged()
                        txt_toolbar_comment_title.text = "댓글(" + tmp.size.toString() + ")"

                    }
                }
            }
        })
    }
}
