package com.qxb.kotlin.data

/**
 * Created by root on 17-6-2.
 */
data class PostResult(
        val page: List<String>,
        val postlist: List<PostItem>,
        val link: List<Int>
);