package com.dbs.interviewtest.posts.data.api

import com.dbs.interviewtest.posts.data.model.Post
import retrofit2.Response

interface ApiHelper {

    suspend fun getPosts(): Response<List<Post>>
}