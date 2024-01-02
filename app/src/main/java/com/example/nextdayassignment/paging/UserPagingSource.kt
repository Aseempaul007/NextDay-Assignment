package com.example.nextdayassignment.paging

import androidx.paging.PagingSource
import com.example.nextdayassignment.data.remote.api.UserApi
import com.example.nextdayassignment.data.remote.model.Data
import java.lang.Exception

class UserPagingSource(val api: UserApi) : PagingSource<Int, Data>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        return try {
            val position = params.key ?: 1
            val response = api.getUsers(position).body()
            LoadResult.Page(
                data = response!!.data,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (position == response.total_pages) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}