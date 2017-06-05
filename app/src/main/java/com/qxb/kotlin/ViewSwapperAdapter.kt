package com.qxb.kotlin

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.qxb.kotlin.fragment.HomeFragment
import org.buffer.adaptablebottomnavigation.adapter.FragmentStateAdapter

/**
 * Created by root on 17-5-31.
 */
class ViewSwapperAdapter(fm: FragmentManager) : FragmentStateAdapter(fm) {

    private val COUNT: Int = 3;

    override fun getItem(position: Int): Fragment? {

        when (position) {
            INDEX_HOME -> {
                return HomeFragment.newInstance(TYPE_KMNY);
            };
            INDEX_ATTENTION -> {
                return HomeFragment.newInstance(TYPE_XXFB)
            };
            INDEX_FUNCTION -> {
                return HomeFragment.newInstance(TYPE_ZXTS)
            };
        }
        return null;
    }


    override fun getCount(): Int {
        return COUNT;
    }

    companion object {
        private val INDEX_HOME = 0;
        private val INDEX_ATTENTION = 1;
        private val INDEX_FUNCTION = 2;


        /***
         *
         */
        private val TYPE_KMNY = "1";
        private val TYPE_ZXTS = "3";
        private val TYPE_XXFB = "2";

    }

}