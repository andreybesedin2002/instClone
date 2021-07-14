package com.example.project.DB.User

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UsersDao {

    @Query("SELECT primaryPhoto FROM User WHERE idUser LIKE :userId ")
    fun getPrimaryPhoto(userId: Int) : String

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

}