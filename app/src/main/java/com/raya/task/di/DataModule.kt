package com.raya.task.di

import android.content.Context
import androidx.room.Room
import com.raya.task.data.network.UserAPI
import com.raya.task.data.repository.local_db.NewAppDataBase
import com.raya.task.data.repository.UserDataSource
import com.raya.task.data.repository.UserRepository
import com.raya.task.data.repository.local_db.UserDetailesDao
import com.raya.task.data.repository.local_db.UserLocalDataSource
import com.raya.task.data.repository.local_db.UserLocalDataSourceImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Singleton
    @Provides
    fun providAppContext(@ApplicationContext app: Context) = app

    @Singleton // Tell Dagger-Hilt to create a singleton accessible everywhere in ApplicationCompenent (i.e. everywhere in the application)
    @Provides
    fun provideYourDatabase(@ApplicationContext app: Context) = Room.databaseBuilder(app, NewAppDataBase::class.java, "db").fallbackToDestructiveMigration().build() // The reason we can construct a database for the repo

    @Singleton
    @Provides
    fun provideSdDao(db: NewAppDataBase) = db.userDetailesDao() // The reason we can implement a Dao for the database

    @Singleton
    @Provides
    fun providesReportDataSource(userAPI: UserAPI): UserDataSource{
        return UserRepository(userAPI)
    }

    @Singleton
    @Provides
    fun provideServiceDirectoryLocalDataSource(userDetailesDao: UserDetailesDao): UserLocalDataSource {
        return UserLocalDataSourceImp(userDetailesDao)
    }
}