package com.example.ituneapisintegrationmvvm.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ituneapisintegrationmvvm.data.models.NewsResponse
import com.example.ituneapisintegrationmvvm.data.remote.ITuneAPIService
import com.example.ituneapisintegrationmvvm.utilities.API_KEY
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class ITuneRepository @Inject constructor(private val iTuneAPIService: ITuneAPIService) {

    /**
     * mutable live data which is being initial api call
     */
    private val newsResponse: MutableLiveData<NewsResponse> = MutableLiveData()

    fun getNewsByCountry(country: String): MutableLiveData<NewsResponse> {
        iTuneAPIService.getNewsByCountry(country, API_KEY).enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                Log.d("API_NEWS", "OnSuccess")
                response.let {
                    newsResponse.value = response.body()
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.d("API_NEWS", "OnFailure")
            }

        })

        return newsResponse
    }

    suspend fun getNewsByTopic(): NewsResponse {
        return iTuneAPIService.getNewsByTopic("tesla", API_KEY)
    }
}