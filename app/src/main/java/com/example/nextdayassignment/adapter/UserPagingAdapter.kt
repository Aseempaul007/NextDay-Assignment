package com.example.maxmavenindiaassignment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.maxmavenindiaassignment.viewmodel.UserViewmodel
import com.example.nextdayassignment.R
import com.example.nextdayassignment.data.remote.model.Data
import com.example.nextdayassignment.databinding.UserItemBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserPagingAdapter(
    private val context: Context,
    private val viewmodel: UserViewmodel,
    private val state: Boolean
) : PagingDataAdapter<Data, UserPagingAdapter.UserViewHolder>(comparator) {

    inner class UserViewHolder(val binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {
        val comparator = object : DiffUtil.ItemCallback<Data>() {
            override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = UserItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        if (state) {
            holder.binding.favourite.visibility = View.VISIBLE
        } else {
            holder.binding.favourite.visibility = View.INVISIBLE
        }

        val imageUrl = getItem(position)?.avatar
        holder.binding.firstName.text = getItem(position)?.first_name
        holder.binding.lastName.text = getItem(position)?.last_name
        holder.binding.userEmail.text = getItem(position)?.email
        Glide.with(context).load(imageUrl)
            .centerCrop()
            .placeholder(R.drawable.picture)
            .into(holder.binding.userImg)

        holder.binding.favourite.setOnClickListener {
            Toast.makeText(context, "Added to Favourites!", Toast.LENGTH_SHORT).show()
            CoroutineScope(Dispatchers.IO).launch {
                viewmodel.addUser(getItem(position)!!)
            }
        }
    }
}