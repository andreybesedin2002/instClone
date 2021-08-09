package com.example.project.REST

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface GetDataService {
    @GET("/chats")
    fun getChats(): Call<List<ChatModel>>
    @GET("/test")
    fun getMainPuplications(): Call<List<MainPublicationModel>>
    @GET("/profile")
    fun getProfile(): Call<List<MessageMedel>>
    @GET("/chatMessages")
    fun getChatMessages(): Call<List<MessageMedel>>
    @GET("/commentsx")
    fun getComments(@Query("postId")postId: Int): Call<List<CommentsModel>>
}