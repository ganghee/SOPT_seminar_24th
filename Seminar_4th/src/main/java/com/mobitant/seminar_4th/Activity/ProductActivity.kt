package com.mobitant.seminar_4th.Activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.mobitant.seminar_4th.Adapter.EpisodeOverviewRecyclerViewAdapter
import com.mobitant.seminar_4th.Data.EpisodeOverviewData
import com.mobitant.seminar_4th.R
import kotlinx.android.synthetic.main.activity_product.*
import kotlinx.android.synthetic.main.toolbar_product.*
import java.util.*

class ProductActivity : AppCompatActivity() {

    lateinit var episodeOverviewRecyclerViewAdapter: EpisodeOverviewRecyclerViewAdapter
    lateinit var title: String
    var product_id: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        configureTitleBar()
        configureRecyclerView()


    }

    //배열에 에피소드 데이터 넣기
    private fun configureRecyclerView() {
        var dataList: ArrayList<EpisodeOverviewData> = ArrayList()
        dataList.add(
            EpisodeOverviewData(
                product_id, 0, "http://sopt.org/wp/wp-content/uploads/2014/01/24_SOPT-LOGO_COLOR-1.png",
                "에피소드 1", 1, "2019-04-01"))
        dataList.add(
            EpisodeOverviewData(
                product_id, 1, "http://sopt.org/wp/wp-content/uploads/2014/01/24_SOPT-LOGO_COLOR-1.png",
                "에피소드 2", 10, "2019-04-02"))
        dataList.add(
            EpisodeOverviewData(
                product_id, 2, "http://sopt.org/wp/wp-content/uploads/2014/01/24_SOPT-LOGO_COLOR-1.png",
                "에피소드 3", 100, "2019-04-03"))
        dataList.add(
            EpisodeOverviewData(
                product_id, 3, "http://sopt.org/wp/wp-content/uploads/2014/01/24_SOPT-LOGO_COLOR-1.png",
                "에피소드 4", 1000, "2019-04-04"))
        dataList.add(
            EpisodeOverviewData(
                product_id, 4, "http://sopt.org/wp/wp-content/uploads/2014/01/24_SOPT-LOGO_COLOR-1.png",
                "에피소드 5", 10000, "2019-04-05"))

        episodeOverviewRecyclerViewAdapter = EpisodeOverviewRecyclerViewAdapter(this, dataList)
        rv_episode_overview_list.adapter = episodeOverviewRecyclerViewAdapter
        rv_episode_overview_list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
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
