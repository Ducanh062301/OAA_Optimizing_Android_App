package com.example.productimageapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Size
import com.example.productimageapp.databinding.ItemImageBinding

class ImageAdapter(
    private val onClick: (ImageItem) -> Unit
) : ListAdapter<ImageItem, ImageAdapter.VH>(Diff) {

    class VH(val b: ItemImageBinding) : RecyclerView.ViewHolder(b.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH =
        VH(ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = getItem(position)
        holder.b.tvTitle.text = item.title
        holder.b.imageView.load(item.listUrl) {
            placeholder(android.R.drawable.ic_menu_gallery)
            error(android.R.drawable.ic_dialog_alert)
            size(Size.ORIGINAL)
        }
        holder.b.root.setOnClickListener { onClick(item) }
    }

    object Diff : DiffUtil.ItemCallback<ImageItem>() {
        override fun areItemsTheSame(a: ImageItem, b: ImageItem) = a.id == b.id
        override fun areContentsTheSame(a: ImageItem, b: ImageItem) = a == b
    }
}