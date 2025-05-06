package com.bianca_paun.lostandfoundapp.data.repositories.user

import com.bianca_paun.lostandfoundapp.models.user.UserModel


interface UserRepository {
    suspend fun insertUserWithRole(user: UserModel)
    suspend fun getUserByUsername(username: String): UserModel?
    suspend fun getUserByUsernameAndPassword(username: String, password: String): UserModel?

}