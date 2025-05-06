package com.bianca_paun.lostandfoundapp.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bianca_paun.lostandfoundapp.data.repositories.user.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginFragmentViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    val username = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val loginSuccess = MutableLiveData<Boolean>()

    fun login() {
        val user = username.value.orEmpty()
        val pass = password.value.orEmpty()

        viewModelScope.launch(Dispatchers.IO) {
            val found = userRepository.getUserByUsernameAndPassword(user, pass)
            withContext(Dispatchers.Main) {
                loginSuccess.value = found != null
            }
        }
    }
}
