package com.bianca_paun.lostandfoundapp.models.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class UserModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val username: String,
    val password: String,

    )
