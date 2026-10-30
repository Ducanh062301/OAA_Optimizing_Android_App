package com.example.productimageappoptimizever

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.example.productimageappoptimizever.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivDetail.load(intent.getStringExtra("url")) {
            placeholder(android.R.drawable.ic_menu_gallery)
            error(android.R.drawable.ic_dialog_alert)
            crossfade(true)
        }
    }
}