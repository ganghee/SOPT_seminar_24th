package com.mobitant.seminar_6th.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.mobitant.seminar_6th.Activity.WebtoonActivity
import com.mobitant.seminar_6th.Data.EpisodeOverviewData
import com.mobitant.seminar_6th.R
import kotlinx.android.synthetic.main.rv_item_episode_overview.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity
import java.util.*

class EpisodeOverviewRecyclerViewAdapter(val ctx: Context, var dataList: ArrayList<EpisodeOverviewData>) :
    RecyclerView.Adapter<EpisodeOverviewRecyclerViewAdapter.Holder>() {
    override fun onBindViewHolder(holder: Holder, position: Int) {

        Glide.with(ctx)
            .load(dataList[position].thumnail)
            .into(holder.img_thumbnail)
        holder.title.text = dataList[position].title
        holder.description.text = "조회수 " + dataList[position].hits.toString()
        //에피소드 목록의 데이터가 웹툰 내용 화면으로 전달된다.
        holder.container.onClick {
            ctx.startActivity<WebtoonActivity>(
                "title" to dataList[position].title,
                "idx" to dataList[position].idx,
                "chapter" to dataList[position].chapter
            )
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_episode_overview, viewGroup, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var container = itemView.ll_rv_item_episode_overview_container
        var img_thumbnail = itemView.img_rv_item_episode_overview_thumbnail
        var title = itemView.txt_rv_item_episode_overview_title
        var description = itemView.txt_rv_item_episode_overview_description
    }
}