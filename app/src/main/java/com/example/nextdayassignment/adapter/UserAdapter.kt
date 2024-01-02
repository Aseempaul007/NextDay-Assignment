package com.example.maxmavenindiaassignment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.maxmavenindiaassignment.viewmodel.UserViewmodel
import com.example.nextdayassignment.R
import com.example.nextdayassignment.data.remote.model.Data
import com.example.nextdayassignment.databinding.UserItemBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserAdapter(
    private val context: Context,
    private val viewmodel: UserViewmodel,
    private val users: List<Data>,
    private val state: Boolean
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    inner class UserViewHolder(val binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = UserItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return UserViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        if (state) {
            holder.binding.favourite.visibility = View.VISIBLE
        } else {
            holder.binding.favourite.visibility = View.INVISIBLE
        }

        val imageUrl = users.get(position).avatar
        holder.binding.firstName.text = users.get(position).first_name
        holder.binding.lastName.text = users.get(position).last_name
        holder.binding.userEmail.text = users.get(position).email
        Glide.with(context).load(imageUrl)
            .centerCrop()
            .placeholder(R.drawable.picture)
            .into(holder.binding.userImg)

        holder.binding.favourite.setOnClickListener {
            Toast.makeText(context, "Added to Favourites", Toast.LENGTH_SHORT).show()
            CoroutineScope(Dispatchers.IO).launch {
                viewmodel.addUser(users.get(position))
            }
        }
    }
}