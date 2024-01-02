package com.example.nextdayassignment.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.nextdayassignment.data.remote.model.Data

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(data: Data)

    @Query("SELECT * FROM Data")
    fun showAllUsers(): LiveData<List<Data>>

}