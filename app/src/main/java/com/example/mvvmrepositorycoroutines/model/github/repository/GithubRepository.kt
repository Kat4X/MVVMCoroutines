package com.example.mvvmrepositorycoroutines.model.github.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.mvvmrepositorycoroutines.model.github.Github
import com.example.mvvmrepositorycoroutines.model.github.api.GithubBuilder
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

object GithubRepository {
    private val TAG = GithubRepository::class.java.simpleName
    var job: CompletableJob? = null

    fun getSingleUser(username: String): LiveData<Github> {
        job = Job()
        return object : LiveData<Github>() {
            override fun onActive() {
                super.onActive()
                job?.let {
                    CoroutineScope(IO + it).launch {

                        val github = GithubBuilder.githubService.getSingleUser(username)
                        if (github.isSuccessful)
                            withContext(Main) {
                                value = github.body()
                                it.complete()
                            }
                        else withContext(IO) {
                            Log.e("TAG", github.errorBody().toString())
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