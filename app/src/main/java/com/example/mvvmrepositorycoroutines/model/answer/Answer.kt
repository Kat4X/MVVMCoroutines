package com.example.mvvmrepositorycoroutines.model.answer

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Answer {

    @SerializedName("answer")
    @Expose
    var answer: String? = null
    @SerializedName("forced")
    @Expose
    var forced: Boolean? = null
    @SerializedName("image")
    @Expose
    var image: String? = null

}