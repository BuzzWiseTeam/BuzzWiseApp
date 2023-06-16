package com.example.buzzwiseapp.ui.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.buzzwiseapp.data.api.ApiConfig
import com.example.buzzwiseapp.data.model.UserPreference
import com.example.buzzwiseapp.data.response.DataUser
import com.example.buzzwiseapp.data.response.JobDetailResponse
import com.example.buzzwiseapp.data.response.ProfileResponse
import com.example.buzzwiseapp.ui.jobdetail.JobViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileViewModel(private val pref: UserPreference) : ViewModel(){

    private val _detailProfile = MutableLiveData<DataUser>()
    val detailProfile: LiveData<DataUser> = _detailProfile

    private val _loadingScreen = MutableLiveData<Boolean>()
    val loadingScreen: LiveData<Boolean> = _loadingScreen

    companion object {
        private const val TAG = "ProfileViewModel"
    }
    fun logout() {
        viewModelScope.launch {
            pref.logout()
        }
    }

    fun getProfile(id: String) {
        _loadingScreen.value = true

        val cilent = ApiConfig.getApiService().getProfile(id)
        cilent.enqueue(object : Callback<ProfileResponse> {
            override fun onResponse(
                call: Call<ProfileResponse>,
                response: Response<ProfileResponse>
            ) {
                _loadingScreen.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _detailProfile.value = responseBody.data ?: DataUser()
                        Log.d(TAG, responseBody.message.toString())
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                _loadingScreen.value = false
                Log.e(TAG, "onFailure: Gagal")
            }
        })
    }

}