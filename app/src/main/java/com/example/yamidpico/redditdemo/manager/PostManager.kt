package com.example.yamidpico.redditdemo.manager

import com.example.yamidpico.redditdemo.model.HotPostContainer
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class PostManager {

    companion object {
        private const val BASE_URL = "https://www.reddit.com"
        private const val CONNECTION_TIMEOUT_IN_SECONDS = 10L
        private const val READ_TIMEOUT_IN_SECONDS = 10L
        private const val KEY_REQUEST_HEADER_AUTHORIZATION = "authorization"
    }

    private val retrofit by lazy {
        val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .connectTimeout(CONNECTION_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            //.addInterceptor(requestInterceptor)
            .build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    private val postService by lazy {
        retrofit.create(RedditService::class.java)
    }


    fun getHotPost(): Flowable<HotPostContainer?> {
        return postService.getHotPost().map {
            return@map it.body()
        }.subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread())
    }
}