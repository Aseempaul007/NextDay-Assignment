package com.example.maxmavenindiaassignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.maxmavenindiaassignment.repository.UserRepository
import com.example.nextdayassignment.data.local.dao.UserDao
import com.example.nextdayassignment.data.remote.model.Data
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewmodel @Inject constructor(
    private var repository: UserRepository,
    private val userDao: UserDao
) : ViewModel() {

    val allUsers: LiveData<List<Data>>

    init {
        allUsers = repository.showAllUsers
    }

    val getUsers = repository.getUsers().cachedIn(viewModelScope)

    suspend fun addUser(data: Data) {
        repository.addUser(data)
    }
}