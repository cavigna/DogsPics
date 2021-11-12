package com.example.dogspics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dogspics.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

//    private val viewModel: PerroViewModel by viewModels {
//        PerroModelFactory((application as PerroApplication).repositorio)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}

/*
https://dog.ceo/api/breed/hound/basset/images
 */