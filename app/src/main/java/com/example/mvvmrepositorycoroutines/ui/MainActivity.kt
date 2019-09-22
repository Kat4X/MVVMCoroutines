package com.example.mvvmrepositorycoroutines.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.mvvmrepositorycoroutines.viewmodel.MainViewModel
import com.example.mvvmrepositorycoroutines.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewModel()
        updateAnswerTenTimes()
        initButton()
    }

    private fun updateAnswerTenTimes() {
        CoroutineScope(Main).launch {
            for (i in 1..10) {
                viewModel.updateAnswer()
                delay(3000)
            }
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.answer.observe(this, Observer {
            text_view.text = it.answer
            Glide.with(this)
                .load(it.image)
                .into(image_view)
        })
    }

    private fun initButton() {
        button.setOnClickListener {
            intent = Intent(this, GitActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.cancelJobs()
    }
}
