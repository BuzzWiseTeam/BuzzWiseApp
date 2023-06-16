package com.example.buzzwiseapp.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.buzzwiseapp.data.model.UserPreference
import kotlinx.coroutines.launch

class ProfileViewModel(private val pref: UserPreference) : ViewModel(){

    companion object {
        private const val TAG = "ProfileViewModel"
    }

    fun logout() {
        viewModelScope.launch {
            pref.logout()
        }
    }
}