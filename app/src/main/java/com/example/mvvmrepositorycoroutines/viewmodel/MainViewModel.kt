package com.example.mvvmrepositorycoroutines.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.mvvmrepositorycoroutines.model.answer.Answer
import com.example.mvvmrepositorycoroutines.model.answer.repository.AnswerRepository

class MainViewModel : ViewModel() {
    private val _answer: MutableLiveData<Answer> = MutableLiveData()

    val answer: LiveData<Answer> = Transformations.switchMap(_answer) {
        AnswerRepository.getAnswer()
    }

    fun cancelJobs() {
        AnswerRepository.cancelJobs()
    }

    fun updateAnswer() {
        _answer.value = Answer()
    }
}