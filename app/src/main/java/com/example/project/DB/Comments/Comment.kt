package com.example.project.DB.Comments

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Comment")
data class Comment(
    @PrimaryKey
    val idcomment: Int,
    val idpost: Int,
    val time : String,
    val numComment: Int,
    val idIser: Int,
    val dataComment: String,
    var likes:Int
   // val replyComments: List<ReplyComment>
)
