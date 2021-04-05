package com.raya.task.data.repository.local_db

import com.raya.task.data.model.UserResponse
import javax.inject.Inject

class UserLocalDataSourceImp @Inject constructor(val userDetailesDao: UserDetailesDao) :
    UserLocalDataSource {
    override suspend fun insertUserData(userData: UserResponse) {
        userDetailesDao.insertUserResponse(userData)
    }

    override suspend fun getUserData(): UserResponse {
        return userDetailesDao.getUserResponse()
    }
}