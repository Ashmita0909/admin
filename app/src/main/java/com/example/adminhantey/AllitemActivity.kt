package com.example.adminhantey

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adminhantey.adapter.AddItemAdapter
import com.example.adminhantey.databinding.ActivityAllitemBinding

class AllitemActivity : AppCompatActivity() {
    private val binding:ActivityAllitemBinding by lazy {
        ActivityAllitemBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        val menuFoodName= listOf("Burger","Pasta","momo","Burger","Pasta","Dal Bhat")
        val menuItemPrice= listOf("Rs500","Rs600","Rs700","Rs80","Rs50","Rs60","Rs76","Rs80")
        val menuImage= listOf(
            R.drawable.gallery,
            R.drawable.gallery,
            R.drawable.gallery,
            R.drawable.gallery,
            R.drawable.gallery,
            R.drawable.gallery,
        )
        val adapter= AddItemAdapter( ArrayList(menuFoodName),ArrayList(menuItemPrice),ArrayList(menuImage))
         binding.MenuRecyclerView.layoutManager=LinearLayoutManager(this)
        binding.MenuRecyclerView.adapter=adapter

    }
}