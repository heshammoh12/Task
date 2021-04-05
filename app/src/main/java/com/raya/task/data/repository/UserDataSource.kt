package com.raya.task.data.repository

import com.raya.movie.data.repository.APIResult
import com.raya.task.data.model.UserDetailsResponse
import com.raya.task.data.model.UserResponse

interface UserDataSource {
    suspend fun getAllUsers(): APIResult<UserResponse>
    suspend fun getUserDetailes(id:String): APIResult<UserDetailsResponse>
}