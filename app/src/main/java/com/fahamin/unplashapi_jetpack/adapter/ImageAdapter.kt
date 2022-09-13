package com.fahamin.unplashapi_jetpack.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.fahamin.unplashapi_jetpack.R
import com.fahamin.unplashapi_jetpack.UnsplashModel
import com.fahamin.unplashapi_jetpack.databinding.ItemLayoutBinding
import com.fahamin.unplashapi_jetpack.utils.ItemClickListener
import com.squareup.picasso.Picasso

class ImageAdapter(var list: List<UnsplashModel>, var itemClickListener: ItemClickListener) :
    RecyclerView.Adapter<ImageAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        // If your layout file is something_awesome.xml then your binding class will be SomethingAwesomeBinding
        // Since our layout file is item_movie.xml, our auto generated binding class is ItemMovieBinding

        fun setData(imageUrl: String) {
            Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.lod)
                .into(binding.ivImage)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(list[position].urls?.small.toString())

        holder.itemView.setOnClickListener(View.OnClickListener {
            itemClickListener.onItemClick(position)
        })
    }

    override fun getItemCount(): Int {
        return list.size
    }
}