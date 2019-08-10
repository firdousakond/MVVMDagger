package com.firdous.mvvmdagger.network

import com.firdous.mvvmdagger.model.Post
import io.reactivex.Observable
import retrofit2.http.GET

interface PostApi {

    @GET("/posts")
    fun getPosts() : Observable<List<Post>>
}