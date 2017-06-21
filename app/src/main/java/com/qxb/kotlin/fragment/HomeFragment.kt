package com.qxb.kotlin.fragment

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.view.menu.ActionMenuItem
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
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
        private val ARG_TYPE_ID = "arg_type_id";
        fun newInstance(typeId: String): HomeFragment {
            val homeFragment: HomeFragment = HomeFragment();
            var bundle: Bundle = Bundle();
            bundle.putString(ARG_TYPE_ID, typeId);
            homeFragment.arguments = bundle;
            return homeFragment;

        }
    }

    lateinit var mTypeId: String;
    lateinit var mRecyclerView: RecyclerView;
    lateinit var mSwipeRefreshLayout: SwipeRefreshLayout;
    lateinit var mBtnNext: Button;
    var mCurrentPage: Int = 1;

    var mIsLoadingDataFlag: Boolean = false;
    var mHomeListRecyclerAdapter: HomeListRecyclerAdapter? = null;
    var mRootView: View? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
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
            doGetNextData()
        }
        initRecyclerView();
        initSwipeRefreshLayout();
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_home,menu);
    }


    private fun initRecyclerView() {
        mRecyclerView = view!!.findViewById(R.id.recycler_view) as RecyclerView;
        mRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        val onScrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                if (dy > 0) { //向下滑动
                    var linearLayoutManager = recyclerView?.layoutManager as LinearLayoutManager;
                    var lastCompletelyVisibleItemPosition = linearLayoutManager.findLastCompletelyVisibleItemPosition()
                    var lastPosition: Int = linearLayoutManager.findLastVisibleItemPosition();
                    var itemCount = mHomeListRecyclerAdapter!!.itemCount;
                    println("lastPosition = $lastPosition lastCompletelyVisibleItemPosition = $lastCompletelyVisibleItemPosition   itemCount = $itemCount")
                    if (!mIsLoadingDataFlag and (lastCompletelyVisibleItemPosition + 1 >= itemCount)) {
//                        Toast.makeText(context, "自动加载下一页", Toast.LENGTH_SHORT).show();
                        doGetNextData();
                    }
                }

            }
        }
        mRecyclerView.addOnScrollListener(onScrollListener);
    }

    private fun initSwipeRefreshLayout() {
        mSwipeRefreshLayout = view!!.findViewById(R.id.swipe_refresh_layout) as SwipeRefreshLayout
        mSwipeRefreshLayout.setOnRefreshListener {
            doRefresh();
        }
    }

    private fun doRefresh() {
        mCurrentPage = 1;
        doGetData()
    }

    private fun doGetNextData() {
        mCurrentPage += 1;
        doGetData();
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mTypeId = arguments.getString(ARG_TYPE_ID);
        doGetData();
    }

    fun doGetData() {
        mIsLoadingDataFlag = true;
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
                { throwable ->
                    println("error = " + throwable.message)
                    mIsLoadingDataFlag = false;
                    mCurrentPage -= 1;
                    mCurrentPage = if (mCurrentPage < 1) 1 else mCurrentPage;
                }
    }

    fun showRequestData(items: LinkedList<CommonItem>) {
        mIsLoadingDataFlag = false;
        mSwipeRefreshLayout.isRefreshing = false;
        mBtnNext.text = "下一页 " + (mCurrentPage + 1);
        if (null == mHomeListRecyclerAdapter || mCurrentPage == 1) {
            mHomeListRecyclerAdapter = HomeListRecyclerAdapter(context);
            mRecyclerView.adapter = mHomeListRecyclerAdapter;
        }
        mHomeListRecyclerAdapter?.addItems(items);
        println("size = ${mHomeListRecyclerAdapter?.itemCount}")
    }

}