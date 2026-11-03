package com.example.productimageappoptimizever

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.productimageappoptimizever.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ImageAdapter { item ->
            startActivity(
                Intent(this, DetailActivity::class.java)
                    .putExtra("url", item.detailUrl)
            )
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = adapter


        val count = intent.getIntExtra("count", 100)
        val offset = intent.getIntExtra("seedOffset", 0)
        val badEvery = intent.getIntExtra("badEvery", 0)
        val items = ImageRepository.getImages(count + offset).drop(offset).map {
            if (badEvery > 0 && it.id % badEvery == 0) {
                it.copy(listUrl = "https://invalid.invalid/img.jpg")
            } else it
        }
        adapter.submitList(items)
    }
}
