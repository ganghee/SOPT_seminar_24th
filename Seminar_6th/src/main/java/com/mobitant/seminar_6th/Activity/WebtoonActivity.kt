package com.mobitant.seminar_6th.Activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.mobitant.seminar_6th.Adapter.EpisodeContentRecyclerViewAdapter
import com.mobitant.seminar_6th.Network.ApplicationController
import com.mobitant.seminar_6th.Network.Get.GetEpisodeContentResponse
import com.mobitant.seminar_6th.Network.NetworkService
import com.mobitant.seminar_6th.R
import kotlinx.android.synthetic.main.activity_webtoon.*
import kotlinx.android.synthetic.main.toolbar_webtoon.*
import org.jetbrains.anko.startActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class WebtoonActivity : AppCompatActivity() {

    lateinit var episodeContentRecyclerViewAdapter: EpisodeContentRecyclerViewAdapter
    lateinit var title: String
    var idx = -1
    var chapter = -1
    val networkService: NetworkService by lazy{
        ApplicationController.instance.networkService
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webtoon)

        configureTitleBar()
        configureRecyclerView()

    }

    private fun configureRecyclerView() {
        var dataList: ArrayList<String> = ArrayList()

        episodeContentRecyclerViewAdapter = EpisodeContentRecyclerViewAdapter(this, dataList)
        rv_episode_content.adapter = episodeContentRecyclerViewAdapter
        rv_episode_content.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        getEpisodeContentResponse()

    }

    private fun getEpisodeContentResponse() {
        val getEpisodeContentResponse = networkService.getEpisodeContentResponse(
            "application/json", idx)

        getEpisodeContentResponse.enqueue(object : Callback<GetEpisodeContentResponse> {
            override fun onFailure(call: Call<GetEpisodeContentResponse>, t: Throwable) {
                Log.e("EpisodeContent Fail", t.toString())
            }

            override fun onResponse(
                call: Call<GetEpisodeContentResponse>,
                response: Response<GetEpisodeContentResponse>
            ) {
                if (response.isSuccessful) {
                    if (response.body()!!.status == 200) {
                        val tmp= response.body()!!.data
                        episodeContentRecyclerViewAdapter.dataList = tmp!!.imgs
                        episodeContentRecyclerViewAdapter.notifyDataSetChanged()

                    }
                }
            }
        })
    }

    //웹툰 내용으로부터 웹툰 제목과 제품id, 에피소드id를 가져와
    //웹툰 제목은 타이틀바에 표시하고
    //제품id와 에피소드id는 디폴트 값인경우 화면이 사라진다.
    private fun configureTitleBar(){
        title = intent.getStringExtra("title")
        idx = intent.getIntExtra("idx", -1)
        chapter = intent.getIntExtra("chapter", -1)
        if(idx == -1 || chapter == -1) {finish()}

        txt_toolbar_webtoon_title.text = title
        //댓글 리스트 화면으로 이동
        btn_toolbar_webtoon_comment.setOnClickListener {
            startActivity<CommentActivity>(
                "idx" to idx,
                "chapter" to chapter
            )
        }
        btn_toolbar_webtoon_back.setOnClickListener {
            finish()
        }
    }
}
