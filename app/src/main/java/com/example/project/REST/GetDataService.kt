package com.example.project.REST

import retrofit2.Call
import retrofit2.http.GET




interface GetDataService {
    @GET("/chats")
    fun getChats(): Call<List<MessageMedel>>
    @GET("/test")
    fun getMainPuplications(): Call<List<MainPublicationModel>>
    @GET("/profile")
    fun getProfile(): Call<List<MessageMedel>>
    @GET("/chatMessages")
    fun getChatMessages(): Call<List<MessageMedel>>
}