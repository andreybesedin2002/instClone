package com.example.project.DB.Messages

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Messages")
data class Message(
    @PrimaryKey(autoGenerate = true) val id:Int? = null,
    val idUser:Int?= null,
    val idChat: Int? = null,
    val text: String)
