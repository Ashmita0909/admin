package com.example.adminhantey

import android.os.Bundle
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.adminhantey.databinding.ActivityAddItemBinding

class AddItemActivity : AppCompatActivity() {
    private val  binding: ActivityAddItemBinding by lazy {
        ActivityAddItemBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
binding.selectimage.setOnClickListener{
    pickimage.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
}
        binding.backButton.setOnClickListener {
            finish()
        }
    }
    private val pickimage=registerForActivityResult(ActivityResultContracts.PickVisualMedia()){
        uri->
        if (uri != null) {
            binding.selectedimage.setImageURI(uri)

        }
    }
}