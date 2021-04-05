package com.raya.task.data.repository

import com.raya.movie.data.repository.APIResult
import com.raya.movie.data.repository.BaseRepository
import com.raya.task.data.model.UserDetailsResponse
import com.raya.task.data.model.UserResponse
import com.raya.task.data.network.UserAPI
import javax.inject.Inject

class UserRepository @Inject constructor(val api : UserAPI) : BaseRepository() , UserDataSource {
    override suspend fun getAllUsers(): APIResult<UserResponse> =
    getAPIResult(safeApiCall { api.getUsers() })

    override suspend fun getUserDetailes(id: String): APIResult<UserDetailsResponse> =
        getAPIResult(safeApiCall { api.getUserDetailes(id) })
}