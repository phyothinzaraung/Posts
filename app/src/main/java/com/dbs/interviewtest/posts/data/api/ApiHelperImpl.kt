package com.dbs.interviewtest.posts.data.api

import com.dbs.interviewtest.posts.data.model.Post
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService): ApiHelper{
    override suspend fun getPosts(): Response<List<Post>> = apiService.getPosts()
}