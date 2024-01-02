package com.example.nextdayassignment.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.maxmavenindiaassignment.viewmodel.UserViewmodel
import com.example.nextdayassignment.R
import com.example.nextdayassignment.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var viewModel: UserViewmodel? = null
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(UserViewmodel::class.java)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.search -> {
                    navController.navigate(R.id.action_favoriteFragment_to_userFragment)
                }

                R.id.favorites -> {
                    navController.navigate(R.id.action_userFragment_to_favoriteFragment)
                }
            }
            true
        }
    }
}