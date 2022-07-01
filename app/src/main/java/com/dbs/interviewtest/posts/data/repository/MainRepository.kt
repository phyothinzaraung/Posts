package com.dbs.interviewtest.posts.data.repository

import com.dbs.interviewtest.posts.data.api.ApiHelper
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiHelper: ApiHelper) {

    suspend fun getPosts() = apiHelper.getPosts()
}