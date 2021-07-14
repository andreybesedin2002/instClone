package com.example.project.DB.Comments

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ReplyComment(
    @PrimaryKey
    val idreplycomment: Int,
    val idComment: Int,
    val idIser: Int,
    val dataComment: String,
    val likes:Int,


)
