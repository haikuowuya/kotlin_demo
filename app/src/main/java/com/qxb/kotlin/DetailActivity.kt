package com.qxb.kotlin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.qxb.kotlin.data.PostResult
import com.qxb.kotlin.util.RetrofitUtils
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by root on 17-6-2.
 */
class DetailActivity : AppCompatActivity() {
    companion object {
        private val EXTRA_TID = "extra_tid";
        fun actionDetail(context: Context, tid: String) {
            val intent: Intent = Intent(context, DetailActivity::class.java);
            intent.putExtra(EXTRA_TID, tid);
            context.startActivity(intent);
        }
    }

    lateinit var mTvText: TextView;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        mTvText = findViewById(R.id.tv_text) as TextView;
        var tid = intent.getStringExtra(EXTRA_TID);
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
        mTvText.text = postResult.toString();
    }
}
