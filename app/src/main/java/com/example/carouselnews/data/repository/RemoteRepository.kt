package com.example.carouselnews.data.repository

import com.example.carouselnews.data.model.NewsResponse
import com.example.carouselnews.di.ApiService
import com.example.carouselnews.utils.ResponseWrapper
import javax.inject.Inject

class RemoteRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getNewsData(): ResponseWrapper<NewsResponse?> {
        return try {
            val res = apiService.getNews()
            if (res.isSuccessful && res.body() != null) {
                ResponseWrapper.Success(res.body())
            } else {
                ResponseWrapper.Error(res.code(), res.message())
            }
        } catch (e: Exception) {
            ResponseWrapper.Error(500, "Something went wrong. Please try again")
        }
    }
}
