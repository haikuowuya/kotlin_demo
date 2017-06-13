package com.qxb.kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.qxb.kotlin.util.RetrofitUtils
import org.buffer.adaptablebottomnavigation.adapter.ViewSwapperAdapter
import org.buffer.adaptablebottomnavigation.view.AdaptableBottomNavigationView
import org.buffer.adaptablebottomnavigation.view.ViewSwapper
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


/**
 * *
 * *
 * *         | |         (==)        | |  \ \ \ \ \ \ \ \ \ \ \
 * *         | |                     | |
 * *         | |                     | |
 * *         | |                     | |
 * *         | |                     | |                  ) ) ) ) )
 *
 * *         | |                     | |                ) ) ) ) )
 *
 * *         | |                     | |               ) ) ) ) )
 *
 * *         | |                     | |             ) ) ) ) )
 *
 * *         | |                     | |           ) ) ) ) )
 *
 * *         | |                     | |         ) ) ) ) )
 *
 * *         | |                     | |       ) ) ) ) )
 *
 * *         | |                     | |    ) ) ) ) )
 *
 * *         | |                     | |  ) ) ) ) )
 *
 * *         | |                     | |  ) ) ) ) )
 * *         | |                     | |      ( ( ( ( (
 *
 * *         | |                     | |         ( ( ( ( (
 *
 * *         | |                     | |           ( ( ( ( (
 *
 * *         | |                     | |              ( ( ( ( (
 *
 * *         | |                     | |                ( ( ( ( (
 *
 * *         | |                     | |
 * *         | |                     | |
 * *         | |                     | |
 * *         | |                     | |
 * *         | |                     | |
 * *         | |                     | |
 * *         | |                     | |
 * *         | |                     | |
 * *         | |                     | |
 * *         | |                     | |
 * *         | |         | |
 * *         | |         | |
 * *         | |         | |
 * *         | |         | |
 * *         | |         | |
 * *         | |         | |
 * *         | |         | |
 * *         | |         | |
 * *         | |         | |
 * *         | |         | |
 * *         | |         | |
 * *         | |
 * *         |  --------------------
 * *         -----------------------
 * *
 * *
 * *
 * *
 * *
 * *
 * *
 * *
 */
class MainActivity : AppCompatActivity() {
    lateinit var mViewSwapperAdapter: ViewSwapperAdapter
    lateinit var mBottomNavigationView: AdaptableBottomNavigationView;
    lateinit var mSwapperView: ViewSwapper;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);
        mBottomNavigationView = findViewById(R.id.view_bottom_navigation) as AdaptableBottomNavigationView;
        mSwapperView = findViewById(R.id.view_swapper) as ViewSwapper;
        mViewSwapperAdapter = ViewSwapperAdapter(supportFragmentManager);
        mSwapperView!!.adapter = mViewSwapperAdapter;
        mBottomNavigationView!!.setupWithViewSwapper(mSwapperView);
        BottomNavigationViewHelper.disableShiftMode(mBottomNavigationView);
    }
}