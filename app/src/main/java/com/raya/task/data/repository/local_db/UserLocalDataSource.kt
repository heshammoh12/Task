package com.raya.task.data.repository.local_db

import com.raya.task.data.model.UserResponse

interface UserLocalDataSource {

    suspend fun insertUserData(userData: UserResponse)

    suspend fun getUserData():UserResponse
}