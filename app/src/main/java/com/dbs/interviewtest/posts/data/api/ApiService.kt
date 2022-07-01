package com.dbs.interviewtest.posts.data.api

import com.dbs.interviewtest.posts.data.model.Post
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("posts")
    suspend fun getPosts(): Response<List<Post>>
}