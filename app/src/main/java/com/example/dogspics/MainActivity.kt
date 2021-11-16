package com.example.dogspics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.dogspics.application.PerroApplication
import com.example.dogspics.databinding.ActivityMainBinding
import com.example.dogspics.viewmodel.PerroModelFactory
import com.example.dogspics.viewmodel.PerroViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    private val viewModel: PerroViewModel by viewModels {
        PerroModelFactory((application as PerroApplication).repositorio)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home_menu-> navController.navigate(R.id.homeFragment)
                R.id.listado_menu-> navController.navigate(R.id.listFragment)
                R.id.favoritos_menu-> navController.navigate(R.id.favFragment)
            }
            true
        }


    }


}

