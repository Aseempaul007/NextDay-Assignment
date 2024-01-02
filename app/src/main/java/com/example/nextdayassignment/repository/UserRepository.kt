package com.example.maxmavenindiaassignment.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.nextdayassignment.data.local.dao.UserDao
import com.example.nextdayassignment.data.remote.api.UserApi
import com.example.nextdayassignment.data.remote.model.Data
import com.example.nextdayassignment.paging.UserPagingSource
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val api: UserApi,
    private val userDao: UserDao
) {

    fun getUsers() = Pager(
        config = PagingConfig(
            pageSize = 6,
            enablePlaceholders = true,
            initialLoadSize = 10,
            prefetchDistance = 3
        ),
        pagingSourceFactory = { UserPagingSource(api) }
    ).liveData

    suspend fun addUser(data: Data) {
        userDao.addUser(data)
    }

    val showAllUsers: LiveData<List<Data>> = userDao.showAllUsers()
}