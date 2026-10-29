package com.example.productimageapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.load
import coil.size.Size
import com.example.productimageapp.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivDetail.load(intent.getStringExtra("url")) {
            placeholder(android.R.drawable.ic_menu_gallery)
            error(android.R.drawable.ic_dialog_alert)
            size(Size.ORIGINAL)
        }
    }
}