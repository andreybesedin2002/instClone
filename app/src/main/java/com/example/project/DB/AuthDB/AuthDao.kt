package com.example.project.DB.AuthDB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.project.DB.Messages.Message

@Dao
interface AuthDao {
    @Query("SELECT EXISTS(SELECT * FROM AuthData WHERE login LIKE :login_ and password LIKE :password_)")
    fun check(login_: String, password_: String): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(authData: AuthData)

}