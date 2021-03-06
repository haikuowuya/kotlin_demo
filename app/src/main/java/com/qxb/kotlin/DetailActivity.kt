package com.qxb.kotlin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.qxb.kotlin.fragment.DetailFragment

/**
 * Created by root on 17-6-2.
 */
class DetailActivity : AppCompatActivity() {
    companion object {
        private val EXTRA_TID = "extra_tid";
        private val EXTRA_TITLE = "extra_title";
        fun actionDetail(context: Context, tid: String, title: String) {
            val intent: Intent = Intent(context, DetailActivity::class.java);
            intent.putExtra(EXTRA_TID, tid);
            intent.putExtra(EXTRA_TITLE, title);
            context.startActivity(intent);
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_HOME or ActionBar.DISPLAY_HOME_AS_UP or ActionBar.DISPLAY_SHOW_TITLE;
//        title = intent.getStringExtra(EXTRA_TITLE);
        supportActionBar?.title = intent.getStringExtra(EXTRA_TITLE);

        var tid = intent.getStringExtra(EXTRA_TID);
        supportFragmentManager.beginTransaction().add(R.id.frame_container, DetailFragment.newInstance(tid)).commit();
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            android.R.id.home -> {
                finish();
                return true
            };
        }
        return super.onOptionsItemSelected(item)
    }

}
