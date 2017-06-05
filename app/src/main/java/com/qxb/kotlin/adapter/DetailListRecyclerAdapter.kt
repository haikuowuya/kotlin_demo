package com.qxb.kotlin.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.qxb.kotlin.R
import com.qxb.kotlin.data.PostItem

/**
 * Created by root on 17-6-5.
 */
class DetailListRecyclerAdapter(val context: Context, val items: List<PostItem>) : RecyclerView.Adapter<DetailListRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(container: ViewGroup?, p1: Int): ViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.list_detail_item, container, false);
        return ViewHolder(itemView);
    }

    override fun onBindViewHolder(viewHolder: ViewHolder?, position: Int) {
        viewHolder!!.tvContent.text = Html.fromHtml(items[position].content);
        viewHolder!!.tvTime.text = Html.fromHtml(items[position].postdate);
        viewHolder!!.tvAutor.text = items[position].author;
        viewHolder!!.tvLevel.text = items[position].tid;
        Glide.with(context).load(items[0].micon).placeholder(R.mipmap.ic_launcher).into(viewHolder!!.ivPhoto);

    }

    override fun getItemCount(): Int {
        return items.size;
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvContent: TextView = itemView.findViewById(R.id.tv_content) as TextView;
        var tvAutor: TextView = itemView.findViewById(R.id.tv_author) as TextView;
        var tvTime: TextView = itemView.findViewById(R.id.tv_time) as TextView;
        var ivPhoto: ImageView = itemView.findViewById(R.id.iv_photo) as ImageView;
        var tvLevel: TextView = itemView.findViewById(R.id.tv_level) as TextView;
    }

}
