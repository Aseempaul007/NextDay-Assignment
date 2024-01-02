package com.example.nextdayassignment.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.maxmavenindiaassignment.adapter.UserAdapter
import com.example.maxmavenindiaassignment.viewmodel.UserViewmodel
import com.example.nextdayassignment.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment() {

    lateinit var binding: FragmentFavoriteBinding
    lateinit var viewModel: UserViewmodel
    lateinit var adapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(UserViewmodel::class.java)

        binding.progressBar.visibility = View.VISIBLE

        viewModel.allUsers.observe(viewLifecycleOwner, Observer { entities ->
            if (entities.size <= 0) {
                binding.noFav.visibility = View.VISIBLE
            } else {
                binding.noFav.visibility = View.INVISIBLE
            }

            binding.progressBar.visibility = View.INVISIBLE
            adapter = UserAdapter(requireContext(), viewModel, entities, false)
            binding.favoriteRecyclerview.adapter = adapter
            binding.favoriteRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        })

        return binding.root
    }

}