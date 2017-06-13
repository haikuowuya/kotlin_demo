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
class HomeListRecyclerAdapter(val context: Context) : RecyclerView.Adapter<HomeListRecyclerAdapter.ViewHolder>() {
    init {
        setHasStableIds(true);
    }
    private var mItems:LinkedList<CommonItem> = LinkedList();
    override fun onCreateViewHolder(container: ViewGroup?, p1: Int): ViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.list_home_item, container, false);
        return ViewHolder(itemView);
    }

    override fun onBindViewHolder(viewHolder: ViewHolder?, position: Int) {
        viewHolder!!.tvContent.text = mItems[position].subject;
        viewHolder!!.tvTime.text = mItems[position].postdate;
        viewHolder!!.tvAutor.text = mItems[position].author;
        viewHolder.itemView.setOnClickListener {
            DetailActivity.actionDetail(context, mItems[position].tid, mItems[position].subject);
        }
    }

    override fun getItemCount(): Int {
        return mItems.size;
    }

    fun addItems(items: List<CommonItem>) {
        mItems.addAll(items);
        notifyDataSetChanged();
    }



    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvContent: TextView = itemView.findViewById(R.id.tv_content) as TextView;
        var tvAutor: TextView = itemView.findViewById(R.id.tv_author) as TextView;
        var tvTime: TextView = itemView.findViewById(R.id.tv_time) as TextView;
    }

}