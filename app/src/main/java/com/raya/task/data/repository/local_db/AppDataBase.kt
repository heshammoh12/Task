package com.raya.task.data.repository.local_db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.raya.task.data.model.UserResponse

@Database(entities = [UserResponse::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class NewAppDataBase : RoomDatabase() {
    abstract fun userDetailesDao(): UserDetailesDao
}