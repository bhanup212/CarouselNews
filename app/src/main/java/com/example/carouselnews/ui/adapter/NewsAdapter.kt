package com.example.carouselnews.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.carouselnews.data.model.NewsResponseItem
import com.example.carouselnews.databinding.NewsCardItemLayoutBinding
import com.example.carouselnews.utils.TimeAgo
import java.util.*
import kotlin.collections.ArrayList

class NewsAdapter(context: Context) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private val inflater = LayoutInflater.from(context)
    private val newsList = ArrayList<NewsResponseItem>()

    fun updateNews(list: ArrayList<NewsResponseItem>) {
        newsList.clear()
        newsList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = NewsCardItemLayoutBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val match = newsList[position]
        holder.bind(match)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    class ViewHolder(private val binding: NewsCardItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(newsResponseItem: NewsResponseItem) {
            Glide.with(binding.image).load(newsResponseItem.banner_url).into(binding.image)
            binding.titleTv.text = newsResponseItem.title
            binding.descriptionTv.text = newsResponseItem.description
            binding.date.text = TimeAgo.toDuration(Date().time - newsResponseItem.time_created)
        }
    }
}
