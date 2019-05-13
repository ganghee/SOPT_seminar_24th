package com.mobitant.seminar_3rd.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.mobitant.seminar_3rd.Activity.ProductActivity
import com.mobitant.seminar_3rd.Data.ProductOverviewData
import com.mobitant.seminar_3rd.R
import kotlinx.android.synthetic.main.rv_item_product_overview.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity

//view와 Data class의 item을 연결시켜 주는 classAdapter
class ProductOverviewRecyclerViewAdapter (val ctx: Context, val dataList: ArrayList<ProductOverviewData>): RecyclerView.Adapter<ProductOverviewRecyclerViewAdapter.Holder>(){
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): Holder {
        val view:View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_product_overview,viewGroup, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        Glide.with(ctx)
            .load(dataList[position].img_url)
            .into(holder.img_thumbnail)
        holder.title.text = dataList[position].title
        holder.num_like.text = "♥" + dataList[position].num_like.toString()
        holder.author.text = dataList[position].author

        //하나의 item이 클릭이 될 때 이벤트
        holder.container.onClick {
            ctx.startActivity<ProductActivity>(
                "product_id" to dataList[position].product_id,
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