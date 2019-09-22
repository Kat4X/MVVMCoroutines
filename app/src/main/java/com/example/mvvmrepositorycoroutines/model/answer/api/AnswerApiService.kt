package com.example.mvvmrepositorycoroutines.model.answer.api

import com.example.mvvmrepositorycoroutines.model.answer.Answer
import retrofit2.http.GET

interface AnswerApiService {
    @GET("/api")
    suspend fun getAnswer(): Answer
}