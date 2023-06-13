package com.example.buzzwiseapp.data.api

import com.example.buzzwiseapp.data.response.DataItem
import com.example.buzzwiseapp.data.response.JobDetailResponse
import com.example.buzzwiseapp.data.response.JobResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("jobs/alljobs")
    fun getAllJobs(): Call<JobResponse>

    @GET("/jobs/{userId}")
    fun getDetailStory(
        @Path("userId") userId: String
    ): Call<JobDetailResponse>
}
