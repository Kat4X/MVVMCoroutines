package com.example.mvvmrepositorycoroutines.model

import com.example.mvvmrepositorycoroutines.model.answer.Answer

object Singleton {

    val answer: Answer by lazy {
        Answer()
    }
}