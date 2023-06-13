package com.example.buzzwiseapp.data.response

import com.google.gson.annotations.SerializedName

data class JobResponse(

	@field:SerializedName("data")
	val data: List<DataItem>,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("error")
	val error: Boolean
)

data class DataItem(

	@field:SerializedName("companyProfileImage")
	val companyProfileImage: String? = null,

	@field:SerializedName("dateAndTime")
	val dateAndTime: String? = null,

	@field:SerializedName("companyName")
	val companyName: String? = null,

	@field:SerializedName("jobDescription")
	val jobDescription: String? = null,

	@field:SerializedName("location")
	val location: String? = null,

	@field:SerializedName("requiredSkill")
	val requiredSkill: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("jobType")
	val jobType: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("email")
	val email: String? = null
)

data class JobDetailResponse(

	@field:SerializedName("data")
	val data: DataItem? = null,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("error")
	val error: Boolean
)
