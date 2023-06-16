package com.example.buzzwiseapp.data.response

import com.google.gson.annotations.SerializedName

data class ProfileResponse(

	@field:SerializedName("data")
	val data: DataUser? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class DataUser(

	@field:SerializedName("skills")
	val skills: Any? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("userProfileImage")
	val userProfileImage: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("about")
	val about: Any? = null,

	@field:SerializedName("location")
	val location: Any? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("headline")
	val headline: Any? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)
