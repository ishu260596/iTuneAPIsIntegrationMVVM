package com.example.ituneapisintegrationmvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ituneapisintegrationmvvm.data.models.NewsResponse
import com.example.ituneapisintegrationmvvm.data.remote.ITuneAPIService
import com.example.ituneapisintegrationmvvm.repository.ITuneRepository
import com.example.ituneapisintegrationmvvm.utilities.API_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ITuneViewModel @Inject constructor(private val iTuneRepo: ITuneRepository) : ViewModel() {

    private val _newsResponse: MutableLiveData<NewsResponse> = MutableLiveData()
    val newsResponse: LiveData<NewsResponse> = _newsResponse

    fun getNewsByCountry(country: String): MutableLiveData<NewsResponse> {
        return iTuneRepo.getNewsByCountry(country)
    }

    fun getNewsByTopic() {
        viewModelScope.launch(Dispatchers.IO) {
            _newsResponse.postValue(iTuneRepo.getNewsByTopic())
        }
    }
}