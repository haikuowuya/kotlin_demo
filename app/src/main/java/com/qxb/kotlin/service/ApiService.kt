package com.qxb.kotlin.service

import com.qxb.kotlin.data.PostResult
import com.qxb.kotlin.data.ThreadResult
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

/**
 * Created by root on 17-6-1.
 */
interface ApiService {

    @GET("gs_android_threads.php?fid=2&filter=typeid&perpage=20" )
    fun getHswz(@Query("typeid")typeid:String, @Query("page") page: Int): Observable<ThreadResult>;

    @GET("gs_android_topicinfo.php?perpage=20")
    fun getPostDetail(@Query("tid")tid:String, @Query("page")page:Int):Observable<PostResult>
}