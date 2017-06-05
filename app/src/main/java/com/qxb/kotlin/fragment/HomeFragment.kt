package com.qxb.kotlin.fragment

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.Button
import com.qxb.kotlin.R
import com.qxb.kotlin.adapter.HomeListRecyclerAdapter
import com.qxb.kotlin.base.BaseFragment
import com.qxb.kotlin.data.CommonItem
import com.qxb.kotlin.util.RetrofitUtils
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.*

/**
 * Created by root on 17-6-1.
 */
class HomeFragment : BaseFragment() {

    companion object {
        private val  ARG_TYPE_ID="arg_type_id";
        fun newInstance(typeId:String ): HomeFragment {
            val homeFragment: HomeFragment = HomeFragment();
            var bundle:Bundle = Bundle();
            bundle.putString(ARG_TYPE_ID, typeId);
            homeFragment.arguments = bundle;
            return homeFragment;

        }
    }

    lateinit  var mTypeId :String ;
    lateinit var mRecyclerView: RecyclerView;
    lateinit var mSwipeRefreshLayout: SwipeRefreshLayout;
    lateinit var mBtnNext: Button;
    var mCurrentPage: Int = 1;
    var mHomeListRecyclerAdapter: HomeListRecyclerAdapter? = null;
    var mRootView: View? = null;
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (null == mRootView) {
            mRootView = inflater?.inflate(R.layout.fragment_home, null);
        }
        var viewParent: ViewParent? = mRootView?.parent;
        if (viewParent != null) {
            (viewParent as ViewGroup).removeView(mRootView);
        }
        return mRootView;
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBtnNext = view!!.findViewById(R.id.btn_next) as Button;
        mBtnNext.setOnClickListener {
            mCurrentPage += 1;
            doGetData()
        }

        mRecyclerView = view!!.findViewById(R.id.recycler_view) as RecyclerView;
        mRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        mSwipeRefreshLayout = view!!.findViewById(R.id.swipe_refresh_layout) as SwipeRefreshLayout
        mSwipeRefreshLayout.setOnRefreshListener {
            doRefresh();
        }
    }

    private fun doRefresh() {
        mCurrentPage = 1;
        doGetData()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mTypeId = arguments.getString(ARG_TYPE_ID);
        doGetData();
    }
    fun doGetData() {
        if (mCurrentPage < 1) {
            mCurrentPage = 1
        }
        RetrofitUtils.getApiService()?.getHswz(mTypeId, mCurrentPage)!!
                .subscribeOn(Schedulers.io())!!
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { println("doOnSubscribe") }
                .subscribe({ (threads) ->
                    showRequestData(threads.common);
                })
                { throwable -> println("error = " + throwable.message) }
    }

    fun showRequestData(items: LinkedList<CommonItem>) {
        mSwipeRefreshLayout.isRefreshing = false;
        mBtnNext.text = "下一页 " + (mCurrentPage + 1);
        if (null == mHomeListRecyclerAdapter) {
            mHomeListRecyclerAdapter = HomeListRecyclerAdapter(context, items);
            mRecyclerView.adapter = mHomeListRecyclerAdapter;
        } else {
            if (mCurrentPage == 1) {
                mHomeListRecyclerAdapter?.clearPreviousData();
            }
            mHomeListRecyclerAdapter?.addItems(items);
        }
        println("size = ${mHomeListRecyclerAdapter?.commonItems?.size}")
    }

}