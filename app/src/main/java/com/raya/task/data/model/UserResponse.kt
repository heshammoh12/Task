package com.raya.task.data.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
data class UserResponse(
    @PrimaryKey(autoGenerate = true) //For Room DB
    @SerializedName("page")
    var page: Int,
    @SerializedName("per_page")
    var perPage: Int,
    @SerializedName("total")
    var total: Int,
    @SerializedName("total_pages")
    var totalPages: Int,
    @SerializedName("data")
    var usersData: List<UserData>,
    @SerializedName("support")
    @Ignore
    var userDetailsSupport: UserDetailsSupport?
){
    constructor(): this(0,0,0,0, listOf(),null)
}

data class UserDetailsSupport(
    @SerializedName("url")
    val url: String,
    @SerializedName("text")
    val text: String
)

data class UserData(
    @SerializedName("id")
    var id: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("avatar")
    val avatar: String
) : Serializable