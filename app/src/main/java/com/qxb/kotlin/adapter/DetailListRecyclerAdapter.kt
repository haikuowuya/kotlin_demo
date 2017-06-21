package com.qxb.kotlin.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.gson.internal.LinkedTreeMap
import com.qxb.kotlin.R
import com.qxb.kotlin.data.PostItem
import com.qxb.kotlin.data.ReplyItem

/**
 * Created by root on 17-6-5.
 */
class DetailListRecyclerAdapter(val context: Context, val items: List<PostItem>) : RecyclerView.Adapter<DetailListRecyclerAdapter.ViewHolder>() {

    var mLayoutInflater: LayoutInflater = LayoutInflater.from(context);
    override fun onCreateViewHolder(container: ViewGroup?, p1: Int): ViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.list_detail_item, container, false);
        return ViewHolder(itemView);
    }

    override fun onBindViewHolder(viewHolder: ViewHolder?, position: Int) {
        viewHolder!!.tvContent.text = Html.fromHtml(items[position].content);
        viewHolder!!.tvTime.text = Html.fromHtml(items[position].postdate);
        viewHolder!!.tvAuthor.text = items[position].author;
        var level = if ("1".equals(items[position].lou)) "楼主" else items[position].lou + "楼";
        viewHolder!!.tvLevel.text = level;
        Glide.with(context).load(items[0].micon).placeholder(R.mipmap.ic_launcher).into(viewHolder!!.ivPhoto);
        var depreply = items[position].depreply;
        if (depreply is List<*>) {
            var list = depreply as List<LinkedTreeMap<String, String>>;
            list.forEach()
            {
                var child: View = mLayoutInflater.inflate(R.layout.layout_reply_item, viewHolder!!.linearReplyContainer, false);
                var tvAuthor = child.findViewById(R.id.tv_author) as TextView;
                tvAuthor.text = it[ReplyItem.TAG_AUTHOR];

                var tvTime = child.findViewById(R.id.tv_time) as TextView;
                tvTime.text = it[ReplyItem.TAG_DATE_LINE];

                var tvContent = child.findViewById(R.id.tv_content) as TextView;
                tvContent.text = it[ReplyItem.TAG_MESSAGE];

                var ivImage = child.findViewById(R.id.iv_photo) as ImageView;
                Glide.with(context).load(it[ReplyItem.TAG_MICON]).placeholder(R.mipmap.ic_launcher).into(ivImage);

                viewHolder!!.linearReplyContainer?.addView(child);

            }


        }

    }

    override fun getItemCount(): Int {
        return items.size;
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvContent: TextView = itemView.findViewById(R.id.tv_content) as TextView;
        var tvAuthor: TextView = itemView.findViewById(R.id.tv_author) as TextView;
        var tvTime: TextView = itemView.findViewById(R.id.tv_time) as TextView;
        var ivPhoto: ImageView = itemView.findViewById(R.id.iv_photo) as ImageView;
        var tvLevel: TextView = itemView.findViewById(R.id.tv_level) as TextView;
        var linearReplyContainer: LinearLayout = itemView.findViewById(R.id.linear_reply_container) as LinearLayout;
    }

}
