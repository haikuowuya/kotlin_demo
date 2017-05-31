package com.qxb.kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.buffer.adaptablebottomnavigation.adapter.ViewSwapperAdapter
import org.buffer.adaptablebottomnavigation.view.AdaptableBottomNavigationView
import org.buffer.adaptablebottomnavigation.view.ViewSwapper

/**
 * Created by root on 17-5-26.
 */
class MainActivity :AppCompatActivity(){
    lateinit  var mViewSwapperAdapter: ViewSwapperAdapter
    lateinit  var mBottomNavigationView: AdaptableBottomNavigationView;
    lateinit  var mSwapperView:ViewSwapper;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);
        mBottomNavigationView = findViewById(R.id.view_bottom_navigation) as AdaptableBottomNavigationView;
        mSwapperView =  findViewById(R.id.view_swapper) as ViewSwapper;
        mViewSwapperAdapter = ViewSwapperAdapter(supportFragmentManager);
        mSwapperView!!.adapter = mViewSwapperAdapter;
        mBottomNavigationView!!.setupWithViewSwapper(mSwapperView);
        BottomNavigationViewHelper.disableShiftMode(mBottomNavigationView);

    }
}