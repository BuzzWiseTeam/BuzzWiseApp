package com.example.buzzwiseapp.data.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class MultiFactor(

	@field:SerializedName("enrolledFactors")
	val enrolledFactors: List<Any?>? = null
)

data class StsTokenManager(

	@field:SerializedName("apiKey")
	val apiKey: String? = null,

	@field:SerializedName("expirationTime")
	val expirationTime: Long? = null,

	@field:SerializedName("accessToken")
	val accessToken: String? = null,

	@field:SerializedName("refreshToken")
	val refreshToken: String? = null
)

data class User(

	@field:SerializedName("apiKey")
	val apiKey: String? = null,

	@field:SerializedName("providerData")
	val providerData: List<ProviderDataItem?>? = null,

	@field:SerializedName("displayName")
	val displayName: String? = null,

	@field:SerializedName("appName")
	val appName: String? = null,

	@field:SerializedName("redirectEventId")
	val redirectEventId: Any? = null,

	@field:SerializedName("authDomain")
	val authDomain: String? = null,

	@field:SerializedName("uid")
	val uid: String? = null,

	@field:SerializedName("photoURL")
	val photoURL: Any? = null,

	@field:SerializedName("emailVerified")
	val emailVerified: Boolean? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("isAnonymous")
	val isAnonymous: Boolean? = null,

	@field:SerializedName("stsTokenManager")
	val stsTokenManager: StsTokenManager? = null,

	@field:SerializedName("phoneNumber")
	val phoneNumber: Any? = null,

	@field:SerializedName("lastLoginAt")
	val lastLoginAt: String? = null,

	@field:SerializedName("multiFactor")
	val multiFactor: MultiFactor? = null,

	@field:SerializedName("tenantId")
	val tenantId: Any? = null,

	@field:SerializedName("email")
	val email: String? = null
)

data class AdditionalUserInfo(

	@field:SerializedName("providerId")
	val providerId: String? = null,

	@field:SerializedName("isNewUser")
	val isNewUser: Boolean? = null
)

data class Data(

	@field:SerializedName("credential")
	val credential: Any? = null,

	@field:SerializedName("additionalUserInfo")
	val additionalUserInfo: AdditionalUserInfo? = null,

	@field:SerializedName("operationType")
	val operationType: String? = null,

	@field:SerializedName("user")
	val user: User? = null
)

data class ProviderDataItem(

	@field:SerializedName("uid")
	val uid: String? = null,

	@field:SerializedName("photoURL")
	val photoURL: Any? = null,

	@field:SerializedName("phoneNumber")
	val phoneNumber: Any? = null,

	@field:SerializedName("displayName")
	val displayName: String? = null,

	@field:SerializedName("providerId")
	val providerId: String? = null,

	@field:SerializedName("email")
	val email: String? = null
)
