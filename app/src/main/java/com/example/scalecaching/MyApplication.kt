package com.example.scalecaching

import android.app.Application
import androidx.room.Room
import com.example.scalecaching.common.AppDatabase

class MyApplication: Application() {
    val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "my-database"
        ).build()
    }
}
