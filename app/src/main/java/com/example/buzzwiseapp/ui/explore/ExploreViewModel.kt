package com.example.buzzwiseapp.ui.explore

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.buzzwiseapp.data.api.ApiConfig
import com.example.buzzwiseapp.data.response.SearchResponse
import com.example.buzzwiseapp.data.response.SimilarWordsItem
import com.example.buzzwiseapp.ui.home.HomeViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ExploreViewModel(): ViewModel() {
    private val _listData = MutableLiveData<List<SimilarWordsItem>>()
    val listData: LiveData<List<SimilarWordsItem>> = _listData

    /*fun getSearchUser(query: String) {
        val client = ApiConfig.getApiService().getProfile(query)
        client.enqueue(object : Callback<SearchResponse> {
            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _listData.value = responseBody.similarWords ?: emptyList()
                    }
                }
            }
            override fun onFailure(call: retrofit2.Call<SearchResponse>, t: Throwable) {
                Log.e("ExploreViewModel", "onFailure: ${t.message}")
            }
        })
    }*/

    companion object {
        private const val TAG = "ExploreViewModel"
    }
}