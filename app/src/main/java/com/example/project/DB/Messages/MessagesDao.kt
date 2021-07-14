package com.example.project.DB.Messages

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MessagesDao {

    @Query("SELECT * FROM MESSAGES WHERE id LIKE :gj and idUser LIKE :userId and idChat LIKE :chatId")
    fun getMessagesfromUserChat(chatId: Int, userId: Int,gj : Int) : Message

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(message: Message)

}