package com.example.productimageappoptimizever

object ImageRepository {
    fun getImages(count: Int): List<ImageItem> = (1..count).map { i ->
        ImageItem(
            id = i,
            title = "Sản phẩm #$i",
            listUrl = "https://picsum.photos/seed/$i/400/300",
            detailUrl = "https://picsum.photos/seed/$i/1600/1200"
        )
    }
}