package com.example.carouselnews.di

import com.example.carouselnews.data.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("/carousell-interview-assets/android/carousell_news.json")
    suspend fun getNews(): Response<NewsResponse>
}