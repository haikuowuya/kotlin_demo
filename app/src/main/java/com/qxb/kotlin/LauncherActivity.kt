package com.qxb.kotlin

import android.app.Activity
import android.content.Intent
import android.os.Bundle

/**
 * 启动入口Activity
 */
class LauncherActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionMain()
        finish();
    }

    fun actionMain() {
        val intent: Intent = Intent(this, MainActivity::class.java);
        startActivity(intent);
    }
}