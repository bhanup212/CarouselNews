package com.example.carouselnews.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carouselnews.data.model.NewsResponse
import com.example.carouselnews.data.repository.RemoteRepository
import com.example.carouselnews.di.module.StorageModule.Companion.IO_DISPATCHER
import com.example.carouselnews.di.module.StorageModule.Companion.MAIN_DISPATCHER
import com.example.carouselnews.utils.ResponseWrapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

class NewsViewModel @Inject constructor(
    private val remoteRepository: RemoteRepository,
    @Named(IO_DISPATCHER) private val ioDispatcher: CoroutineDispatcher,
    @Named(MAIN_DISPATCHER) private val mainDispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val _newsResponse = MutableLiveData<NewsResponse>()
    val newsList: LiveData<NewsResponse> = _newsResponse

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun getNewsData() {
        viewModelScope.launch(ioDispatcher) {
            _isLoading.postValue(true)
            val res = remoteRepository.getNewsData()
            when (res) {
                is ResponseWrapper.Success -> {
                    _newsResponse.postValue(res.data)
                }
                is ResponseWrapper.Error -> _errorMessage.postValue(res.message)
            }
            _isLoading.postValue(false)
        }
    }

    fun sortByDate() {
        viewModelScope.launch(ioDispatcher) {
            newsList.value?.let {
                it.sortBy { it.time_created }
                _newsResponse.postValue(it)
            }
        }
    }

    fun sortByPopular() {
        viewModelScope.launch(ioDispatcher) {
            newsList.value?.let {
                it.sortBy { it.rank }
                _newsResponse.postValue(it)
            }
        }
    }
}
