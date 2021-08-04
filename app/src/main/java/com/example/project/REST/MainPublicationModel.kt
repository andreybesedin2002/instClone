package com.example.project.REST

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MainPublicationModel (

    @SerializedName("idPost")
    @Expose val idPost: String? = null,
    @SerializedName("idUser")
    @Expose val idUser: Int? = null,
    @SerializedName("userName")
    @Expose val userName: Int? = null,
    @SerializedName("userPhoto")
    @Expose val userPhoto: String? = null,
    @SerializedName("postPhoto")
    @Expose val postPhoto: String? = null,
    @SerializedName("text")
    @Expose val likes: String,
)
