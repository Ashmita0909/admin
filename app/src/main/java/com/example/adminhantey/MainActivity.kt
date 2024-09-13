package com.example.adminhantey

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.example.adminhantey.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private val  binding:ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.main.setOnClickListener {
            val intent=Intent(this,AddItemActivity::class.java)
            startActivity(intent)
        }

    }
}