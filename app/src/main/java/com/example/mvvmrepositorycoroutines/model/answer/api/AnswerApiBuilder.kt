package com.example.mvvmrepositorycoroutines.model.answer.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AnswerApiBuilder {
    const val BASE_URL = "https://yesno.wtf/"

    private val retrofitBuilder: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }

    val answerApi: AnswerApiService by lazy {
        retrofitBuilder
            .build()
            .create(AnswerApiService::class.java)
    }
}