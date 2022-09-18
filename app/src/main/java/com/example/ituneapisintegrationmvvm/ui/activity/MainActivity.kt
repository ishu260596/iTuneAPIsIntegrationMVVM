package com.example.ituneapisintegrationmvvm.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.ituneapisintegrationmvvm.databinding.ActivityMainBinding
import com.example.ituneapisintegrationmvvm.viewmodel.ITuneViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var iTuneViewModel: ITuneViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        iTuneViewModel = ViewModelProvider(this)[ITuneViewModel::class.java]
        setContentView(binding.root)
        getNewsDataByCountry()
        getNewsDataByTopic()
    }

    private fun getNewsDataByTopic() {
        iTuneViewModel.getNewsByTopic()
        iTuneViewModel.newsResponse.observe(this) {
            Log.v("API_NEWS", "res by country  ${it.toString()}")
        }
    }

    private fun getNewsDataByCountry() {
        iTuneViewModel.getNewsByCountry("us").observe(this) {
            Log.v("API_NEWS", "res by topic ${it.toString()}")
        }
    }
}