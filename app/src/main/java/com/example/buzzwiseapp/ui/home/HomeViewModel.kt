package com.example.buzzwiseapp.ui.home

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.buzzwiseapp.data.api.ApiConfig
import com.example.buzzwiseapp.data.model.UserPreference
import com.example.buzzwiseapp.data.response.DataItem
import com.example.buzzwiseapp.data.response.JobResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel (): ViewModel(){
    private val _listJob = MutableLiveData<List<DataItem>>()
    val listJob: LiveData<List<DataItem>> = _listJob

    private val _loadingScreen = MutableLiveData<Boolean>()
    val loadingScreen: LiveData<Boolean> = _loadingScreen

    fun getJob() {
        _loadingScreen.value = true
        val cilent = ApiConfig.getApiService().getAllJobs()
        cilent.enqueue(object : Callback<JobResponse> {
            override fun onResponse(
                call: Call<JobResponse>,
                response: Response<JobResponse>
            ) {
                _loadingScreen.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null && !responseBody.error) {
                        _listJob.value = responseBody.data ?: emptyList()
                        Log.d(TAG, responseBody.message.toString())
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<JobResponse>, t: Throwable) {
                _loadingScreen.value = false
                Log.e(TAG, "onFailure2: Failed")
            }
        })
    }

    companion object {
        private const val TAG = "HomeViewModel"
    }
}