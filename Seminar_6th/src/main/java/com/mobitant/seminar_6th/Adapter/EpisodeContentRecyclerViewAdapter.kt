package com.mobitant.seminar_6th.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.mobitant.seminar_6th.R
import kotlinx.android.synthetic.main.rv_item_episode_content.view.*

class EpisodeContentRecyclerViewAdapter (val ctx: Context,var dataList: ArrayList<String>):
        RecyclerView.Adapter<EpisodeContentRecyclerViewAdapter.Holder>(){
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): EpisodeContentRecyclerViewAdapter.Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_episode_content, viewGroup, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(p0: EpisodeContentRecyclerViewAdapter.Holder, p1: Int) {
        Glide.with(ctx)
            .load(dataList[p1])
            .into(p0.img)
    }
    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var img = itemView.webtoon_img
    }
}