package com.qxb.kotlin.fragment

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.qxb.kotlin.R
import com.qxb.kotlin.base.BaseFragment

/**
 * Created by root on 17-6-1.
 */
class HomeFragment : BaseFragment() {
    lateinit var mRecyclerView: RecyclerView;
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_home, container, false);
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mRecyclerView = view!!.findViewById(R.id.recycler_view) as RecyclerView;
    }
}