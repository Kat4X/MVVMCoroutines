package com.example.mvvmrepositorycoroutines.model.github.api

import com.example.mvvmrepositorycoroutines.model.github.Github
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {
    @GET("users/{username}")
    suspend fun getSingleUser(@Path("username") username: String): Response<Github>
}