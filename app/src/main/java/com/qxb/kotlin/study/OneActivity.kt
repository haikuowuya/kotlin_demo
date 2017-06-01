package com.qxb.kotlin.study

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by root on 17-5-31.
 */
class OneActivity :AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(android.R.layout.activity_list_item);
        System.out.print("Hello"); //   print("Hello");
        System.out.println("Hello");// println("Hello");
        var a:Int;//int a ;
        val b:Int =1;//final int y =1;


    }
}