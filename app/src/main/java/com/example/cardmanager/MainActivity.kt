package com.example.cardmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cardmanager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_CardManager)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}