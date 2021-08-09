package com.example.project.REST

import android.widget.ImageView
import android.widget.TextView
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ChatModel (
    @SerializedName("id")
    @Expose
    val idChat: Int? = null,
    @SerializedName("idUser")
    @Expose
    val largeText: String? = null,
    @SerializedName("idChat")
    @Expose
    val smallText: String? = null
)