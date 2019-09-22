package com.example.mvvmrepositorycoroutines.model.github.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GithubBuilder {
    private const val BASE_URL = "https://api.github.com/"

    private val rb: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }

    val githubService: GithubService by lazy {
        rb
            .build()
            .create(GithubService::class.java)
    }
}