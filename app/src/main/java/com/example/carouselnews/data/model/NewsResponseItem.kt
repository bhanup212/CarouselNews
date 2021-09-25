package com.example.carouselnews.data.model

data class NewsResponseItem(
    val banner_url: String,
    val description: String,
    val id: String,
    val rank: Int,
    val time_created: Long,
    val title: String
)