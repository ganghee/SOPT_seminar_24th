package com.mobitant.seminar_6th.Activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.mobitant.seminar_6th.Adapter.EpisodeOverviewRecyclerViewAdapter
import com.mobitant.seminar_6th.Data.EpisodeOverviewData
import com.mobitant.seminar_6th.Network.ApplicationController
import com.mobitant.seminar_6th.Network.Get.GetEpisodeListResponse
import com.mobitant.seminar_6th.Network.NetworkService
import com.mobitant.seminar_6th.R
import kotlinx.android.synthetic.main.activity_product.*
import kotlinx.android.synthetic.main.toolbar_product.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class ProductActivity : AppCompatActivity() {

    lateinit var episodeOverviewRecyclerViewAdapter: EpisodeOverviewRecyclerViewAdapter
    lateinit var title: String
    var product_id: Int = -1
    val networkService: NetworkService by lazy{
        ApplicationController.instance.networkService
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        configureTitleBar()
        configureRecyclerView()


    }

    //배열에 에피소드 데이터 넣기
    private fun configureRecyclerView() {
        var dataList: ArrayList<EpisodeOverviewData> = ArrayList()

        episodeOverviewRecyclerViewAdapter = EpisodeOverviewRecyclerViewAdapter(this, dataList)
        rv_episode_overview_list.adapter = episodeOverviewRecyclerViewAdapter
        rv_episode_overview_list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        getEpisodeListResponse()

    }

    private fun getEpisodeListResponse() {
        val getEpisodeListResponse = networkService.getEpisodeListResponse(
            "application/json", product_id)

        getEpisodeListResponse.enqueue(object : Callback<GetEpisodeListResponse> {
            override fun onFailure(call: Call<GetEpisodeListResponse>, t: Throwable) {
                Log.e("EpisodeList Fail", t.toString())
            }

            //응답이 되었을 때 productOverviewRecyclerViewAdapter에 데이터를 연결 시켜 주어야 하므로
            //이때 연결 시키는 데이터는 서버에 있는 데이터이다.
            //notifyDataSetChanged()를 통해 Adapter에게 표시할 데이터가 실시간으로 변경 되었다는 사실을 알려준다.
            override fun onResponse(
                call: Call<GetEpisodeListResponse>,
                response: Response<GetEpisodeListResponse>
            ) {
                if (response.isSuccessful) {
                    if (response.body()!!.status == 200) {
                        val tmp= response.body()!!.data!!
                        episodeOverviewRecyclerViewAdapter.dataList = tmp.list
                        episodeOverviewRecyclerViewAdapter.notifyDataSetChanged()
                    }
                }
            }
        })
    }

    //mainActivity의 title과 id 데이터를 가져와 titleBar에 보여주기
    private fun configureTitleBar() {
        title = intent.getStringExtra("title")
        product_id = intent.getIntExtra("idx", -1)
        if(product_id == -1) finish()

        txt_toolbar_product_title.text = title

        //하트색이 클릭할 때 마다 바뀐다.
        btn_toolbar_product_like.setOnClickListener {
            btn_toolbar_product_like.isSelected = !btn_toolbar_product_like.isSelected
        }

        //뒤로가기 이미지 버튼을 누를때 화면이 없어짐
        btn_toolbar_product_back.setOnClickListener {
            finish()
        }
    }
}
