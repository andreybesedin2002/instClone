package com.example.project.DB.User

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey
    val idUser: String,
    val primaryPhoto: String
  //  val photos:List<String>

)