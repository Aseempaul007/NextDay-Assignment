package com.example.maxmavenindiaassignment.di

import android.content.Context
import androidx.room.Room
import com.example.nextdayassignment.data.local.dao.UserDao
import com.example.nextdayassignment.data.local.db.UserDb
import com.example.nextdayassignment.data.remote.api.UserApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class Module {

    @Provides
    @Singleton
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://reqres.in/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun getBookApi(retrofit: Retrofit): UserApi {
        return retrofit.create(UserApi::class.java)
    }

    @Singleton
    @Provides
    fun getRoomDbIns(@ApplicationContext context: Context): UserDb {
        synchronized(this) {
            return Room.databaseBuilder(
                context,
                UserDb::class.java,
                "user_db"
            ).build()
        }
    }

    @Provides
    @Singleton
    fun getBookDao(userDb: UserDb): UserDao {
        return userDb.userDao()
    }
}