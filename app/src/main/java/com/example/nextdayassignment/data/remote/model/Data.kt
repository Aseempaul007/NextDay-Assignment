package com.example.nextdayassignment.data.remote.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Data(
    val avatar: String,
    val email: String,
    val first_name: String,
    @PrimaryKey
    val id: Int,
    val last_name: String
)