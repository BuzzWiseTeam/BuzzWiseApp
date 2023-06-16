package com.example.buzzwiseapp.data.api

import com.example.buzzwiseapp.data.LoginPayload
import com.example.buzzwiseapp.data.RegisterPayload
import com.example.buzzwiseapp.data.response.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("jobs/alljobs")
    fun getAllJobs(): Call<JobResponse>

    @GET("jobs/job/{userId}")
    fun getDetailStory(
        @Path("userId") userId: String
    ): Call<JobDetailResponse>

    @POST("/api/users/signUp")
    fun registerUser(
        @Body payload: RegisterPayload
    ): Call<ResponseBody>

    @POST("/api/users/signIn")
    fun loginUser(
        @Body payload: LoginPayload
    ): Call<LoginResponse>

    @GET("users/user/{id}")
    fun getProfile(
        @Path("id") userId: String
    ): Call<ProfileResponse>

}
