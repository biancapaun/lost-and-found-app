package com.bianca_paun.lostandfoundapp.ui.register

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.bianca_paun.lostandfoundapp.data.repositories.user.UserRepository
import com.bianca_paun.lostandfoundapp.models.user.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterFragmentViewModel @Inject constructor(
    val userRepository: UserRepository
) : ViewModel() {

    val logoUrl =
        "https://upload.wikimedia.org/wikipedia/commons/thumb/7/77/Google_Images_2015_logo.svg/1200px-Google_Images_2015_logo.svg.png"

    val username = MutableLiveData<String>()
    val password = ObservableField<String>()

    val isUsernameError: LiveData<Boolean> = username.switchMap { username ->
        val isUsernameValid = username.length <= 3
        MutableLiveData(isUsernameValid)
    }

    val userModel = MutableLiveData<UserModel>()
    val usernameError = MutableLiveData<String?>()
    val passwordError = MutableLiveData<String?>()


    fun isPasswordValid(pwd: String): Boolean {
        return pwd.length >= 6
    }


    fun registerUser() {
        val username = this.username.value ?: ""
        val password = this.password.get() ?: ""

        if (!isPasswordValid(password)) {
            passwordError.value = "Password should contain minimum 6 characters!"
            return
        }

        val user = UserModel(
            username = username,
            password = password,
        )

        viewModelScope.launch(Dispatchers.IO) {
            val existingUser = userRepository.getUserByUsername(username)
            if (existingUser == null) {
                userRepository.insertUserWithRole(user)

                viewModelScope.launch(Dispatchers.Main) {
                    this@RegisterFragmentViewModel.userModel.value = user
                }
            } else {
                viewModelScope.launch(Dispatchers.Main) {
                    usernameError.value = "Username already taken!"
                }

            }

            Log.d("REGISTER", "User: $username | $password")
        }
    }
}