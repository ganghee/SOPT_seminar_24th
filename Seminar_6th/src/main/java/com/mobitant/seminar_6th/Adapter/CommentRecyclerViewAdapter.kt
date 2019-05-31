package com.mobitant.seminar_6th.Adapter

import android.app.AlertDialog
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.mobitant.seminar_6th.DB.SharedPreferenceController
import com.mobitant.seminar_6th.Data.CommentData
import com.mobitant.seminar_6th.Network.ApplicationController
import com.mobitant.seminar_6th.Network.Delete.DeleteMessageResponse
import com.mobitant.seminar_6th.Network.NetworkService
import com.mobitant.seminar_6th.R
import kotlinx.android.synthetic.main.rv_item_comment.view.*
import org.jetbrains.anko.sdk27.coroutines.onLongClick
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class CommentRecyclerViewAdapter(val ctx: Context, var dataList: ArrayList<CommentData>) :
    RecyclerView.Adapter<CommentRecyclerViewAdapter.Holder>() {

    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }
    val builder = AlertDialog.Builder(ctx)

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): CommentRecyclerViewAdapter.Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_comment, viewGroup, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: CommentRecyclerViewAdapter.Holder, position: Int) {
        Glide.with(ctx).load(dataList[position].cmtImg).into(holder.img_thumbnail)
        holder.author.text = dataList[position].name
        holder.comment.text = dataList[position].content
        holder.publish_date.text = dataList[position].writetime

        holder.container.onLongClick {
            builder.setMessage("댓글을 삭제하시겠습니까?")
                .setPositiveButton("확인") { dialogInterface, i ->

                    var cmtIdx = dataList[position].idx
                    val token = SharedPreferenceController.getUserToken(ctx)
                    Log.d("#$#$#$","^^^^^token^^^^^"+token+"^^^^^^^cmtIdx^^^^^^"+cmtIdx)
                    val deleteMessageResponse = networkService.deleteCommentResponse(
                        token ,cmtIdx
                    )
                    deleteMessageResponse.enqueue(object : Callback<DeleteMessageResponse> {
                        override fun onFailure(call: Call<DeleteMessageResponse>, t: Throwable) {
                            Log.e("CommentDelete Fail", t.toString())
                        }

                        override fun onResponse(
                            call: Call<DeleteMessageResponse>,
                            response: Response<DeleteMessageResponse>
                        ) {
                            if (response.isSuccessful) {
                                if (response.body()!!.status == 200) {
                                    Toast.makeText(ctx, "댓글이 삭제 되었습니다.", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    })
                }
                .setNegativeButton("취소") { dialogInterface, i ->
                }.show()
        }
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var container = itemView.ll_rv_item_comment_container
        var img_thumbnail = itemView.img_rv_item_comment_thumbnail
        var author = itemView.txt_rv_item_comment_author
        var comment = itemView.txt_rv_item_comment_comment
        var publish_date = itemView.txt_rv_item_comment_date
    }
}