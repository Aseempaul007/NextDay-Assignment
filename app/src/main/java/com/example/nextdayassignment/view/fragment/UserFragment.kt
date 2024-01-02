package com.example.nextdayassignment.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.maxmavenindiaassignment.adapter.UserPagingAdapter
import com.example.maxmavenindiaassignment.viewmodel.UserViewmodel
import com.example.nextdayassignment.databinding.FragmentUserBinding

class UserFragment : Fragment() {

    lateinit var binding: FragmentUserBinding
    lateinit var viewModel: UserViewmodel
    lateinit var adapter: UserPagingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(UserViewmodel::class.java)

        adapter = UserPagingAdapter(requireContext(), viewModel, true)
        binding.recyclerview.adapter = adapter
        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext())

        viewModel.getUsers.observe(requireActivity()) {
            adapter.submitData(lifecycle, it)
        }

        adapter.addLoadStateListener { loadState ->

            if (loadState.refresh is LoadState.Loading ||
                loadState.append is LoadState.Loading
            )
                binding.progressBar.visibility = View.VISIBLE
            else {
                binding.progressBar.visibility = View.INVISIBLE
                val errorState = when {
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }
                errorState?.let {
                    Toast.makeText(requireActivity(), it.error.toString(), Toast.LENGTH_LONG).show()
                }
            }
        }

        return binding.root
    }

}