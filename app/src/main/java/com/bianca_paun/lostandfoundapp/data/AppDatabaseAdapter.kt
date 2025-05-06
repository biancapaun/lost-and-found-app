package com.bianca_paun.lostandfoundapp.data

import android.content.Context
import androidx.room.Room
import com.bianca_paun.lostandfoundapp.data.repositories.user.UserRepository
import com.bianca_paun.lostandfoundapp.data.repositories.user.UserRepositoryLocal

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppDatabaseAdapter {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        val database =
            Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase.DATABASE_NAME)
                .fallbackToDestructiveMigration(true)
                .build()
        database.openHelper.writableDatabase
        return database
    }


    @Provides
    @Singleton
    fun provideUserRepository(database: AppDatabase): UserRepository {
        return UserRepositoryLocal(database.getUserDao())
    }

}