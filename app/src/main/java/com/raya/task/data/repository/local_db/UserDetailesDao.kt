package com.raya.task.data.repository.local_db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.raya.task.data.model.UserResponse

@Dao
interface UserDetailesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserResponse(userResponse: UserResponse?): Long

    @Query("select * from UserResponse")
    fun getUserResponse(): UserResponse
}