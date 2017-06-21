package com.qxb.kotlin.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.qxb.kotlin.R
import com.qxb.kotlin.adapter.DetailListRecyclerAdapter
import com.qxb.kotlin.base.BaseFragment
import com.qxb.kotlin.data.PostResult
import com.qxb.kotlin.util.RetrofitUtils
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by root on 17-6-2.
 */
class DetailFragment : BaseFragment() {

    companion object {
        private val ARG_TID = "arg_tid";
        fun newInstance(tid: String): DetailFragment {
            val fragment = DetailFragment();
            val bundle = Bundle();
            bundle.putString(ARG_TID, tid);
            fragment.arguments = bundle;
            return fragment;
        }
    }

    lateinit var mRecyclerView: RecyclerView;
    var mDetailListRecyclerAdapter: DetailListRecyclerAdapter? = null;
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_detail, container, false);
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mRecyclerView = view?.findViewById(R.id.recycler_view) as RecyclerView;
        mRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var tid = arguments.getString(ARG_TID);
        doGetData(tid);
    }

    fun doGetData(tid: String) {

        RetrofitUtils.getApiService()?.getPostDetail(tid, 1)!!
                .subscribeOn(Schedulers.io())!!
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { }
                .subscribe({ result ->
                    showData(result);
                })
                { throwable -> println("error = " + throwable.message) }
    }

    fun showData(postResult: PostResult) {
        if (null == mDetailListRecyclerAdapter) {
            mDetailListRecyclerAdapter = DetailListRecyclerAdapter(activity, postResult.postlist);
            mRecyclerView.adapter = mDetailListRecyclerAdapter;
        }
    }

}