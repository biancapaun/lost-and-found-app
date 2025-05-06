package com.bianca_paun.lostandfoundapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bianca_paun.lostandfoundapp.models.user.UserModel


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(model: UserModel)

    @Query("SELECT * FROM UserModel WHERE username = :username LIMIT 1")
    suspend fun getUserByUsername(username: String): UserModel?

    @Query("SELECT * FROM UserModel WHERE username = :username AND password = :password LIMIT 1")
    suspend fun getUserByUsernameAndPassword(username: String, password: String): UserModel?

}