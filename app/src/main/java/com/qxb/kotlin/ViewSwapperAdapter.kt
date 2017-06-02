package com.qxb.kotlin

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.qxb.kotlin.fragment.HomeFragment
import com.qxb.kotlin.fragment.ImageFragment
import org.buffer.adaptablebottomnavigation.adapter.FragmentStateAdapter

/**
 * Created by root on 17-5-31.
 */
class ViewSwapperAdapter(val fm: FragmentManager) : FragmentStateAdapter(fm) {

    private val COUNT: Int = 4;

    override fun getItem(position: Int): Fragment {
        var resId = R.mipmap.ic_launcher;
        when (position) {
            INDEX_HOME -> {
                resId = R.drawable.ic_buffer;
                return HomeFragment.newInstance();
            }
            INDEX_ATTENTION -> resId = R.drawable.ic_heart;
            INDEX_FUNCTION -> resId = R.drawable.ic_retreat;
            INDEX_ME -> resId = R.drawable.ic_four;
        }
        return ImageFragment.newInstance(resId)
    }


    override fun getCount(): Int {
        return COUNT;
    }

    companion object {
        private val INDEX_HOME = 0;
        private val INDEX_ATTENTION = 1;
        private val INDEX_FUNCTION = 2;
        private val INDEX_ME = 3;
//        private val INDEX_BUFFER = 0
//        private val INDEX_RETREAT = 1
//        private val INDEX_VALUES = 2
//        private val INDEX_FOUR = 3
    }
}