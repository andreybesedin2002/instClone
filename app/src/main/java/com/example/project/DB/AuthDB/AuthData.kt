package com.example.project.DB.AuthDB

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "AuthData")
data class AuthData(
    @PrimaryKey val login: String,
    val password: String
)
