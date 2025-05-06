package com.bianca_paun.lostandfoundapp.data.repositories.user

import android.util.Log
import com.bianca_paun.lostandfoundapp.data.dao.UserDao
import com.bianca_paun.lostandfoundapp.models.user.UserModel


class UserRepositoryLocal(private val dao: UserDao) : UserRepository {
    override suspend fun insertUserWithRole(user: UserModel) {
        val existingUser = dao.getUserByUsername(user.username)

        if (existingUser == null) {
            dao.insertUser(user)
        } else {
            Log.d("Register", "Username already exists");
        }

    }

    override suspend fun getUserByUsername(username: String): UserModel? {
        return dao.getUserByUsername(username)
    }

    override suspend fun getUserByUsernameAndPassword(username: String, password: String): UserModel? {
        return dao.getUserByUsernameAndPassword(username, password)
    }

}