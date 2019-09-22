package com.example.mvvmrepositorycoroutines.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isEmpty
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.mvvmrepositorycoroutines.R
import com.example.mvvmrepositorycoroutines.viewmodel.GitViewModel
import kotlinx.android.synthetic.main.activity_git.*

class GitActivity : AppCompatActivity() {
    private lateinit var viewModel: GitViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_git)
        initViewModel()
        initFind()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(GitViewModel::class.java)
        viewModel.setUsername("torvalds")
        viewModel.username.observe(this, Observer {
            it?.apply {
                git_username.text = login
                git_name.text = name
                git_repos.text = publicRepos.toString()
                git_place.text = location
                Glide.with(this@GitActivity)
                    .load(avatarUrl)
                    .into(git_image)
            }
        })
    }

    private fun initFind() {
        git_button.setOnClickListener {
            git_username_et.let {
                if (it.text!!.isEmpty()) git_username_et.error = "Enter a username"
                else {
                    viewModel.setUsername(git_username_et.text.toString().trim())
                    it.error = null
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.cancelJobs()
    }
}
