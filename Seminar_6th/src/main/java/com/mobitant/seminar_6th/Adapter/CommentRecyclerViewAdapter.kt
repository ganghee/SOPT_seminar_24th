package com.mobitant.seminar_6th.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.mobitant.seminar_6th.Data.CommentData
import com.mobitant.seminar_6th.R
import kotlinx.android.synthetic.main.rv_item_comment.view.*
import java.util.*

class CommentRecyclerViewAdapter (val ctx: Context, val dataList: ArrayList<CommentData>): RecyclerView.Adapter<CommentRecyclerViewAdapter.Holder>(){
    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): CommentRecyclerViewAdapter.Holder {
        val view : View =LayoutInflater.from(ctx).inflate(R.layout.rv_item_comment,viewGroup,false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: CommentRecyclerViewAdapter.Holder, position: Int) {
        Glide.with(ctx).load(dataList[position].img_url).into(holder.img_thumbnail)
        holder.author.text = dataList[position].author
        holder.comment.text = dataList[position].comment
        holder.publish_date.text = dataList[position].publish_date
    }

    inner class Holder(itemView:View): RecyclerView.ViewHolder(itemView){
        var container = itemView.ll_rv_item_comment_container
        var img_thumbnail = itemView.img_rv_item_comment_thumbnail
        var author = itemView.txt_rv_item_comment_author
        var comment = itemView.txt_rv_item_comment_comment
        var publish_date = itemView.txt_rv_item_comment_date
    }
}