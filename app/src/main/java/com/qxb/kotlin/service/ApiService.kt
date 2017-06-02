package com.qxb.kotlin.service

import com.qxb.kotlin.data.DemoItem
import com.qxb.kotlin.data.PostResult
import com.qxb.kotlin.data.ThreadResult
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

/**
 * Created by root on 17-6-1.
 */
interface ApiService {
    @GET("query?type=1")
    fun demo(): Observable<List<DemoItem>>;

    @GET("gs_android_threads.php?fid=2&perpage=40")
    fun getHswz(@Query("page") page: Int): Observable<ThreadResult>;
    @GET("gs_android_topicinfo.php?perpage=20")
    fun getPostDetail(@Query("tid")tid:String, @Query("page")page:Int):Observable<PostResult>
}