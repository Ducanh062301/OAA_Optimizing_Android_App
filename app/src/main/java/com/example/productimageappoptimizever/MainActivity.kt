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

        val imageCount = intent.getIntExtra("IMAGE_COUNT", 100)
        val testMode = intent.getStringExtra("TEST_MODE") ?: "NORMAL"

        val items = ImageRepository.getImages(imageCount).map {
            when (testMode) {
                "HIGH_RES" -> {
                    it.copy(listUrl = "https://picsum.photos/seed/${it.id}/3840/2160")
                }
                "BROKEN_URL" -> {
                    if (it.id % 2 == 0) it.copy(listUrl = "https://invalid.url.xyz/broken.jpg") else it
                }
                else -> {
                    it
                }
            }
        }

        adapter.submitList(items)
    }
}
