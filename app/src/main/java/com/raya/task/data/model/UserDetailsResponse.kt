package com.raya.task.data.model

import com.google.gson.annotations.SerializedName

data class UserDetailsResponse(
    @SerializedName("data")
    var userDetails: UserDetails,
    @SerializedName("support")
    var userDetailsSupport: UserDetailsSupport
)

data class Support(
    @SerializedName("url")
    val url: String,
    @SerializedName("text")
    val text: String
)

data class UserDetails(
    @SerializedName("id")
    var id: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("first_name")
    val first_name: String,
    @SerializedName("last_name")
    val last_name: String,
    @SerializedName("avatar")
    val avatar: String
)