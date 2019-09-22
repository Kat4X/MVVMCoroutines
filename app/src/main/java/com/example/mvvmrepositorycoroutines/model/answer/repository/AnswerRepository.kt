package com.example.mvvmrepositorycoroutines.model.answer.repository

import androidx.lifecycle.LiveData
import com.example.mvvmrepositorycoroutines.model.answer.api.AnswerApiBuilder
import com.example.mvvmrepositorycoroutines.model.answer.Answer
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

object AnswerRepository {
    var job: CompletableJob? = null


    fun getAnswer(): LiveData<Answer> {
        job = Job()
        return object : LiveData<Answer>() {
            override fun onActive() {
                super.onActive()
                job?.let {
                    CoroutineScope(IO + it).launch {
                        val answer = AnswerApiBuilder.answerApi.getAnswer()
                        withContext(Main) {
                            value = answer
                            it.complete()
                        }
                    }
                }
            }
        }
    }

    fun cancelJobs() {
        job?.cancel()
    }
}