package com.raya.task.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raya.movie.data.repository.APIResult
import com.raya.task.data.model.UserData
import com.raya.task.data.model.UserDetails
import com.raya.task.data.model.UserDetailsResponse
import com.raya.task.data.model.UserResponse
import com.raya.task.data.repository.UserDataSource
import com.raya.task.data.repository.local_db.UserLocalDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val userDataSource: UserDataSource, val userLocalDataSource: UserLocalDataSource) : ViewModel() {
    private val _users = MutableLiveData<List<UserData>>()
    val users: LiveData<List<UserData>> = _users

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private val _usersDetailes = MutableLiveData<UserDetails>()
    val usersDetailes: LiveData<UserDetails> = _usersDetailes

    suspend fun getAllUsers() {
        _isLoading.postValue(true)
        viewModelScope.launch {
            val result = userDataSource.getAllUsers()
            withContext(Dispatchers.Main) {
                handleUsersResponse(result)
            }

        }
    }

    suspend fun getUserDetailes(id: String) {
        _isLoading.postValue(true)
        viewModelScope.launch {
            val result = userDataSource.getUserDetailes(id)
            withContext(Dispatchers.Main) {
                handleUsersDetailesResponse(result)
            }

        }
    }

    private fun handleUsersDetailesResponse(result: APIResult<UserDetailsResponse>) {
        when (result) {
            is APIResult.Success -> {
                _isLoading.value = false
                _usersDetailes.value = result.data.userDetails
            }
            is APIResult.Error -> {
                _isLoading.value = false
            }
        }
    }

    private fun handleUsersResponse(result: APIResult<UserResponse>) {
        when (result) {
            is APIResult.Success -> {
                _isLoading.value = false
                _users.value = result.data.usersData
                CoroutineScope(Dispatchers.IO).launch{
                    userLocalDataSource.insertUserData(result.data)
                }
            }
            is APIResult.Error -> {
                _isLoading.value = false
                CoroutineScope(Dispatchers.IO).launch{
                    userLocalDataSource.getUserData().let {
//                        delay(3000)
                        withContext(Dispatchers.Main){
                            _users.value = it.usersData
                        }
                    }
                }
            }
        }
    }
}