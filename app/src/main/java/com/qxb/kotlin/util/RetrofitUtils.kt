package com.qxb.kotlin.util

import com.qxb.kotlin.service.ApiService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by root on 17-6-1.
 * http://m.blog.csdn.net/article/details?id=72726177
 * https://segunfamisa.com/posts/using-retrofit-on-android-with-kotlin
 */
class RetrofitUtils private constructor() : Interceptor {
    var mOkHttpClient: OkHttpClient? = null;
    var mApiService: ApiService? = null;

    //初始化操作
    init {
        initOkHttpClient();
        val retrofit = Retrofit.Builder()
                .baseUrl(getBaseUrl())
                .client(mOkHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mApiService = retrofit.create(ApiService::class.java);
    }

    /****
     * 通过静态内部类持有的方式创建
     */
    private object InstanceHolder {
        val INSTANCE = RetrofitUtils();
    }

    //提供对外访问的静态方法
    companion object {
        fun getBaseUrl(): String {
            return "http://www.12345.suzhou.gov.cn/bbs/phoneapi/";
        }

        fun getInstance() = InstanceHolder.INSTANCE;
        fun getApiService() = getInstance().mApiService;

    }


    fun initOkHttpClient() {
        if (null == mOkHttpClient) {
            mOkHttpClient = OkHttpClient.Builder()
                    /* .addInterceptor(this)*/
                    .addNetworkInterceptor(this)
                    .build();
        }
    }

    override fun intercept(chain: Interceptor.Chain?): Response {
        var request = chain!!.request()
        var size: Int = request.url().querySize();
        for (i in (0..size - 1)) {
            var key = request.url().queryParameterName(i);
            var value = request.url().queryParameterValue(i);
            println("key = $key value = $value");
        }

        var response = chain.proceed(request)

//        val source = response.body().source()
//        source.request(java.lang.Long.MAX_VALUE)
//        val buffer = source.buffer()
//        var data = String(buffer.clone().readByteArray());
//        println("data = " + data)

        return response.newBuilder().header("Cache-Control", request.cacheControl().toString()).build()
    }
}
