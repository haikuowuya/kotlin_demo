package com.qxb.kotlin.service

import com.qxb.kotlin.data.DemoItem
import com.qxb.kotlin.data.ThreadResult
import retrofit2.http.GET
import rx.Observable

/**
 * Created by root on 17-6-1.
 */
interface ApiService {
    @GET("query?type=1")
    fun demo(): Observable<List<DemoItem>>;

    @GET("gs_android_threads.php?fid=2&page=1&perpage=20")
    fun getHswz():Observable<ThreadResult>;
}