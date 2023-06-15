package com.example.buzzwiseapp.data

import com.google.gson.annotations.SerializedName

data class RegisterPayload(
    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("email")
    val email: String,

    @field:SerializedName("password")
    val password: String
)