package com.example.buzzwiseapp.ui.jobdetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.buzzwiseapp.data.api.ApiConfig
import com.example.buzzwiseapp.data.response.DataItem
import com.example.buzzwiseapp.data.response.JobDetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JobViewModel : ViewModel (){
    companion object {
        private const val TAG = "DetailJobViewModel"
    }
    private val _detailStory = MutableLiveData<DataItem>()
    val detailStory: LiveData<DataItem> = _detailStory

    private val _loadingScreen = MutableLiveData<Boolean>()
    val loadingScreen: LiveData<Boolean> = _loadingScreen

    fun getDetailStory(userId: String) {
        _loadingScreen.value = true

        val cilent = ApiConfig.getApiService().getDetailStory(userId)
        cilent.enqueue(object : Callback<JobDetailResponse> {
            override fun onResponse(
                call: Call<JobDetailResponse>,
                response: Response<JobDetailResponse>
            ) {
                _loadingScreen.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null && !responseBody.error) {
                        _detailStory.value = responseBody.data ?: DataItem()
                        Log.d(TAG, responseBody.message.toString())
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<JobDetailResponse>, t: Throwable) {
                _loadingScreen.value = false
                Log.e(TAG, "onFailure: Gagal")
            }
        })
    }
}