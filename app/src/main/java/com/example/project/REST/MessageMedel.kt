package com.example.project.REST

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class MessageMedel(
    @SerializedName("id")
    @Expose val id: Int? = null,
    @SerializedName("idUser")
    @Expose val idUser: Int? = null,
    @SerializedName("idChat")
    @Expose val idChat: Int? = null,
    @SerializedName("text")
    @Expose val text: String
)
