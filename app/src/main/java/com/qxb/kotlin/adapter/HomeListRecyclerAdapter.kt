package com.qxb.kotlin.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.qxb.kotlin.R
import com.qxb.kotlin.data.CommonItem

/**
 * Created by root on 17-6-1.
 */
class HomeListRecyclerAdapter(val context: Context, val commonItems: List<CommonItem>) : RecyclerView.Adapter<HomeListRecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(container: ViewGroup?, p1: Int): ViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.list_home_item, container, false);
        return ViewHolder(itemView);
    }

    override fun onBindViewHolder(viewHolder: ViewHolder?, position: Int) {

    }

    override fun getItemCount(): Int {
        return commonItems.size;
    }


    class ViewHolder(val itemView: View): RecyclerView.ViewHolder(itemView) {
        lateinit var textview:TextView  ;

    }

}