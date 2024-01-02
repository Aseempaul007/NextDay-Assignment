package com.example.nextdayassignment.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.nextdayassignment.data.local.dao.UserDao
import com.example.nextdayassignment.data.remote.model.Data

@Database(
    entities = [Data::class],
    version = 1
)
abstract class UserDb : RoomDatabase() {
    abstract fun userDao(): UserDao
}