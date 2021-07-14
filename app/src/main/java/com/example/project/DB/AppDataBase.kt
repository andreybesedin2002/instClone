package com.example.project.DB

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.project.DB.AuthDB.AuthDao
import com.example.project.DB.AuthDB.AuthData
import com.example.project.DB.Comments.Comment
import com.example.project.DB.Comments.CommentsDao
import com.example.project.DB.Comments.ReplyComment
import com.example.project.DB.Messages.Message
import com.example.project.DB.Messages.MessagesDao
import com.example.project.DB.User.User
import com.example.project.DB.User.UsersDao

@Database(entities = [Message::class,Comment::class,ReplyComment::class, User::class,AuthData::class], version =5)
public abstract class AppDataBase : RoomDatabase() {

    abstract fun MessagesDao(): MessagesDao
    abstract fun CommentDao(): CommentsDao
    abstract fun UsersDao(): UsersDao
    abstract fun AuthDao(): AuthDao
}