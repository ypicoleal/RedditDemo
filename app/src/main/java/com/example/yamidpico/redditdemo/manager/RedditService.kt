package com.example.yamidpico.redditdemo.manager

import com.example.yamidpico.redditdemo.model.HotPostContainer
import io.reactivex.Flowable
import retrofit2.Response
import retrofit2.http.GET

interface RedditService {

    @GET("hot.json")
    fun getHotPost(): Flowable<Response<HotPostContainer>>

}
