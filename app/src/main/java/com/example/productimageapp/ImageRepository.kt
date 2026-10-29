package com.example.productimageapp

object ImageRepository {
    fun getImages(count: Int): List<ImageItem> = (1..count).map { i ->
        ImageItem(
            id = i,
            title = "Sản phẩm #$i",
            listUrl = "https://picsum.photos/seed/$i/4000/2700",
            detailUrl = "https://picsum.photos/seed/$i/4000/2700"
        )
    }
}