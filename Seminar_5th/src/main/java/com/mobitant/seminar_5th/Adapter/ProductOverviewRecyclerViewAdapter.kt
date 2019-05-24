package com.mobitant.seminar_5th.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.mobitant.seminar_5th.Activity.ProductActivity
import com.mobitant.seminar_5th.Data.ProductOverviewData
import com.mobitant.seminar_5th.R
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity
import java.util.*

//view와 Data class의 item을 연결시켜 주는 classAdapter
class ProductOverviewRecyclerViewAdapter (var ctx: Context, var dataList: ArrayList<ProductOverviewData>): RecyclerView.Adapter<ProductOverviewRecyclerViewAdapter.Holder>(){

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_product_overview,viewGroup,false)
        return Holder(view)
    }

    override fun getItemCount(): Int =
        dataList.size


    override fun onBindViewHolder(holder: Holder, position: Int) {
        Glide.with(ctx)
            .load(dataList[position].thumnail)
            .into(holder.img_thumbnail)
        holder.title.text = dataList[position].title
        holder.num_like.text = "♥" + dataList[position].likes.toString()
        holder.author.text = dataList[position].name

        holder.container.onClick {
            ctx.startActivity<ProductActivity>(
                "idx" to dataList[position].idx,
                "title" to dataList[position].title
            )
        }
    }

    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView){
        var container = itemView.findViewById(R.id.ll_rv_item_product_overview_container) as LinearLayout
        var img_thumbnail = itemView.findViewById(R.id.img_rv_item_product_overview_thumbnail) as ImageView
        var title = itemView.findViewById(R.id.txt_rv_item_product_overview_title) as TextView
        var num_like = itemView.findViewById(R.id.txt_rv_item_product_overview_numlike) as TextView
        var author = itemView.findViewById(R.id.txt_rv_item_product_overview_author) as TextView
    }

}