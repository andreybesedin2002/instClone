package com.example.project

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.project.DB.AppDataBase
import com.example.project.DB.AuthDB.AuthData
import com.example.project.DB.Comments.Comment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking


class MainAct : AppCompatActivity() {
    companion object {
        lateinit var navView: BottomNavigationView

        lateinit var navController: NavController
        lateinit var appBarConfiguration: AppBarConfiguration

        @SuppressLint("StaticFieldLeak")
        lateinit var toolbar: Toolbar

        var INSTANCE: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDataBase::class.java, "Auth_.db"
            )
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onOpen(db: SupportSQLiteDatabase) {
                        super.onOpen(db)
                        runBlocking {
                            GlobalScope.async(Dispatchers.IO) {

                                Log.i("TAG", "onCreate: gfcgvhjj")
                                PREPOPULATE_DATA.forEach { e ->
                                    getInstance(context).AuthDao().insert(e)
                                }
                                COMMENT_DATA.forEach { e ->
                                    getInstance(context).CommentDao().insert(e)
                                }
                            }
                        }
                    }

                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        Log.i("TAG", "onCreate: gfcgvhjj 1")


                    }
                })
                .build()

        val PREPOPULATE_DATA =
            listOf(AuthData("login@1", "11111111"), AuthData("login@gmail.com", "12345678"))
        val COMMENT_DATA =
            listOf(
                Comment(1, 1, "12:32", 0, 1, "comment1", 5),
                Comment(2, 1, "10:46", 1, 1, "comment2", 4),
                Comment(3, 1, "10:46", 1, 1, "comment3", 4),
                Comment(4, 1, "10:46", 1, 1, "comment4", 4),
                Comment(5, 1, "10:46", 1, 1, "comment5", 3),
                Comment(6, 1, "10:46", 1, 1, "comment6", 4),
                Comment(7, 1, "10:46", 1, 1, "comment7", 4),
                Comment(8, 1, "10:46", 1, 1, "comment8", 6),
                Comment(9, 1, "10:46", 1, 1, "comment9", 4),
                Comment(10, 1, "10:46", 1, 1, "comment10", 8),
                Comment(11, 1, "10:46", 1, 1, "comment11", 4),
                Comment(12, 1, "10:46", 1, 1, "comment12", 14),
                Comment(13, 1, "03:19", 2, 1, "comment13", 3)
            )

    }


    @SuppressLint("ResourceAsColor")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        Log.i("TAG", "onCreate: insert $COMMENT_DATA")

        INSTANCE = buildDatabase(context = applicationContext)

        toolbar = findViewById(R.id.toolbar)
        toolbar.title =null
        setSupportActionBar(toolbar)
        supportActionBar!!.hide()

//        val v: View = layoutInflater.inflate(R.layout.activity_main_toolbar_common_view, null)
//        toolbar.removeAllViews()
//        toolbar.addView(v)

//        myToolbar.setNavigationIcon(R.drawable.ic_launcher_foreground)
//        myToolbar.setLogo(android.R.drawable.alert_light_frame)

        navView = findViewById(R.id.nav_view)
        navController = findNavController(R.id.nav_host_fragment)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_dashboard,
                R.id.navigation_search,
                R.id.navigation_home,
                R.id.navigation_notifications
            )
        )
        navView.visibility = View.GONE
//setupWithNavController(appBarConfiguration,navController)
        toolbar.setupWithNavController(navController, appBarConfiguration)

        //    .setupActionBarWithNavController(this,navController,appBarConfiguration)
        navView.setupWithNavController(navController)


        //        navView.setupWithNavController(navController)
//        setupActionBarWithNavController(navController, appBarConfiguration)

    }
}