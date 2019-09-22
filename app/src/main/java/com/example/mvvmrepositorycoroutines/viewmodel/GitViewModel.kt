package com.example.mvvmrepositorycoroutines.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.mvvmrepositorycoroutines.model.github.Github
import com.example.mvvmrepositorycoroutines.model.github.repository.GithubRepository

class GitViewModel : ViewModel() {
    private val _username: MutableLiveData<String> = MutableLiveData()

    val username: LiveData<Github> = Transformations.switchMap(_username) {
        GithubRepository.getSingleUser(it)
    }

    fun setUsername(username: String) {
        if (_username.value != username) _username.value = username
    }

    fun cancelJobs() {
        GithubRepository.cancelJobs()
    }


}