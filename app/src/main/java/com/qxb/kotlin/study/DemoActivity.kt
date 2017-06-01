package com.qxb.kotlin.study

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.qxb.kotlin.R

//
// from java to kotlin
// https://fabiomsr.github.io/from-java-to-kotlin/
class DemoActivity : AppCompatActivity() {
    //1.
    private  var mTextView : TextView? = null;

//    private val textview:TextView by lazy {
//        findViewById(R.id.tv_hello) as TextView;
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println("sum = " + sum(2,4));
        println("sum1 = " + sum1(3, 5));
        printSum(333,5555);
        printSum1(345, 323);
        varrr();
        loop()
//        mTextView = findViewById(R.id.tv_hello) as TextView?;
        mTextView?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                Toast.makeText(v.context,mTextView?.text.toString(), Toast.LENGTH_LONG).show();
            }})

        mTextView?.setOnClickListener({
            Toast.makeText(this,mTextView?.text.toString(), Toast.LENGTH_LONG).show();
        })

        mTextView?.setOnClickListener { v-> Toast.makeText(this, mTextView?.text.toString()+"kkkkk", Toast.LENGTH_LONG).show();
        };

    }

    fun sum(a:Int , b :Int):Int{return  a+b};

    fun sum1(a:Int, b:Int) =a+b;

    fun printSum(a:Int, b:Int):Unit{
        println("sum $a +$b =   ${a+b}")}

    fun printSum1(a:Int, b:Int){
        println("sum  $a + $b = ${a+b}" )
    }

    fun varrr()
    {
        var a =1;
        var b = "sss";
//        b = null;
        var c:String?="dddd";
        c= null;
        b.length;
        var l = if(c != null)c.length else -1;
        var ll = c?.length;
        println("ll = " + ll)
        var lll = c?.length?:-1;
        println("lll = " + lll)
//        var llll = c!!.length;//NPE  NullPointException


    }
     fun maxOfab(a:Int ,b:Int):Int
       {
         if(a> b)
         {
             return a;
         }
         else
         {
             return b;
         }
     }

    fun maxOfab1(a:Int, b:Int)={
         if(a>b)a else b;
    }

    fun loop()
    {
        var items = listOf("kkkk","dddd","3333");
        for (item in items)
        {
            println("item = " + item );
        }

        for(index in items.indices)
        {
            println("index = $index  item =  ${items[index]}");
        }
         var index = 0;
        while (index < items.size)
        {
            println("while index = $index item =${items[index]}");
            index++;
        }

        for (x in 1..10 step 2) {
            println("x =$x")
        }
        for (x in 9 downTo 0 step 3) {
            println("x =$x")
        }

    }

}
