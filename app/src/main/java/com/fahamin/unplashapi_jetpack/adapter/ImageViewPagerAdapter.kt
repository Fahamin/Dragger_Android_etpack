package com.fahamin.unplashapi_jetpack.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fahamin.unplashapi_jetpack.R
import com.fahamin.unplashapi_jetpack.databinding.ImageFullItemBinding
import com.squareup.picasso.Picasso

class ImageViewPagerAdapter(private val imageUrl: ArrayList<String>?) :
    RecyclerView.Adapter<ImageViewPagerAdapter.ViewPageViewHolder>() {

    // If your layout file is something_awesome.xml then your binding class will be SomethingAwesomeBinding
    // Since our layout file is item_movie.xml, our auto generated binding class is ItemMovieBinding

    class ViewPageViewHolder(val binding: ImageFullItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(imageUrl: String) {
            Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.lod)
                .into(binding.ivImage)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPageViewHolder {
        val binding = ImageFullItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return ViewPageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewPageViewHolder, position: Int) {
        holder.setData(imageUrl!![position])
    }

    override fun getItemCount(): Int {
        return imageUrl!!.size
    }

}