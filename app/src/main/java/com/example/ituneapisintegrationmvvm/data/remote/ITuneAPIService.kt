package com.example.ituneapisintegrationmvvm.data.remote

import androidx.lifecycle.LiveData
import com.example.ituneapisintegrationmvvm.data.models.NewsResponse
import com.example.ituneapisintegrationmvvm.utilities.ABOUT_COUNTRIES
import com.example.ituneapisintegrationmvvm.utilities.ABOUT_ONE_TOPIC
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ITuneAPIService {

    @GET(ABOUT_COUNTRIES)
    fun getNewsByCountry(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): Call<NewsResponse>

    @GET(ABOUT_ONE_TOPIC)
    suspend fun getNewsByTopic(
        @Query("q") q: String,
        @Query("apiKey") apiKey: String
    ): NewsResponse
}