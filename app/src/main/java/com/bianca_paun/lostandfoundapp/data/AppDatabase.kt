package com.bianca_paun.lostandfoundapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bianca_paun.lostandfoundapp.data.dao.UserDao
import com.bianca_paun.lostandfoundapp.models.user.UserModel


@Database(
    entities = [
        UserModel::class,
    ],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao

    companion object {
        const val DATABASE_NAME = "LOST_AND_FOUND"
    }
}