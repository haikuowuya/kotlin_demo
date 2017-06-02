package com.qxb.kotlin.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.qxb.kotlin.DetailActivity
import com.qxb.kotlin.R
import com.qxb.kotlin.data.CommonItem
import java.util.*

/**
 * Created by root on 17-6-1.
 */
class HomeListRecyclerAdapter(val context: Context, val commonItems: LinkedList<CommonItem>) : RecyclerView.Adapter<HomeListRecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(container: ViewGroup?, p1: Int): ViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.list_home_item, container, false);
        return ViewHolder(itemView);
    }

    override fun onBindViewHolder(viewHolder: ViewHolder?, position: Int) {
        viewHolder!!.tvContent.text = commonItems.get(position).subject;
        viewHolder!!.tvTime.text = commonItems.get(position).postdate;
        viewHolder!!.tvAutor.text = commonItems.get(position).author;
        viewHolder.itemView.setOnClickListener {
            DetailActivity.actionDetail(context, commonItems.get(position).tid);
        }
    }

    override fun getItemCount(): Int {
        return commonItems.size;
    }

    fun addItems(items: List<CommonItem>) {
        commonItems.addAll(items);
        notifyDataSetChanged();
    }

    /****
     * 清除之前适配器中的数据
     */
    fun clearPreviousData() = { commonItems.clear(); notifyDataSetChanged(); }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvContent: TextView = itemView.findViewById(R.id.tv_content) as TextView;
        var tvAutor: TextView = itemView.findViewById(R.id.tv_author) as TextView;
        var tvTime: TextView = itemView.findViewById(R.id.tv_time) as TextView;
    }

}