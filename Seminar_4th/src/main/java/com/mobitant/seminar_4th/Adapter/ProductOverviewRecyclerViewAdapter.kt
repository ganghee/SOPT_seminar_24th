package com.mobitant.seminar_4th.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.mobitant.seminar_4th.Activity.ProductActivity
import com.mobitant.seminar_4th.Data.ProductOverviewData
import com.mobitant.seminar_4th.R
import kotlinx.android.synthetic.main.rv_item_product_overview.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity
import java.util.*

//view와 Data class의 item을 연결시켜 주는 classAdapter
class ProductOverviewRecyclerViewAdapter (val ctx: Context, var dataList: ArrayList<ProductOverviewData>): RecyclerView.Adapter<ProductOverviewRecyclerViewAdapter.Holder>(){
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): Holder {
        val view:View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_product_overview,viewGroup, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        Glide.with(ctx)
            .load(dataList[position].thumbnail)
            .into(holder.img_thumbnail)
        holder.title.text = dataList[position].title
        holder.num_like.text = "♥" + dataList[position].likes.toString()
        holder.author.text = dataList[position].author

        //하나의 item이 클릭이 될 때 이벤트
        holder.container.onClick {
            ctx.startActivity<ProductActivity>(
                "idx" to dataList[position].idx,
                "title" to dataList[position].title)
        }
    }

    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView){
        var container = itemView.ll_rv_item_product_overview_container
        var img_thumbnail = itemView.img_rv_item_product_overview_thumbnail
        var title = itemView.txt_rv_item_product_overview_title
        var num_like = itemView.txt_rv_item_product_overview_numlike
        var author = itemView.txt_rv_item_product_overview_author
    }
}