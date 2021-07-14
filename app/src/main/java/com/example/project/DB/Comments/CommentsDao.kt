package com.example.project.DB.Comments

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CommentsDao {

    @Query("SELECT * FROM Comment WHERE idpost LIKE :postId")
    fun getCommetsPost(postId: Int) : List<Comment>


    @Query("SELECT * FROM Comment ")
    fun getAllCommetsPost() : List<Comment>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(comment: Comment)

}