package com.ilham.pokecardid.presentation.auth

import androidx.lifecycle.viewModelScope
import com.ilham.event.data.local.entity.UserEntity
import com.ilham.event.data.manger.UserSessionManager
import com.ilham.event.data.repository.UserRepository
import com.ilham.event.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val userSessionManager: UserSessionManager
) : androidx.lifecycle.ViewModel() {

    private val _loginState = MutableStateFlow<Response<UserEntity>>(Response.Loading())
    val loginState: StateFlow<Response<UserEntity>> = _loginState.asStateFlow()

    private val _registrationState = MutableStateFlow<Response<Long>>(Response.Loading())
    val registrationState: StateFlow<Response<Long>> = _registrationState.asStateFlow()

    private val _loggedInUser = MutableStateFlow<UserEntity?>(null)
    val loggedInUser: StateFlow<UserEntity?> = _loggedInUser.asStateFlow()

    init {
        checkLoggedInUser()
    }

    fun checkLoggedInUser() {
        viewModelScope.launch(Dispatchers.IO) {
            val userId = userSessionManager.getLoggedInUserId()
            if (userId != -1L) {
                val user = userRepository.getUserById(userId)
                _loggedInUser.value = user
            } else {
                _loggedInUser.value = null
            }
        }
    }

    fun register(username: String, email: String, passwordPlain: String) {
        _registrationState.value = Response.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val passwordHash = passwordPlain

                if (userRepository.getUserByUsername(username) != null) {
                    _registrationState.value = Response.Error("Username already exists.")
                    return@launch
                }
                if (userRepository.getUserByEmail(email) != null) {
                    _registrationState.value = Response.Error("Email already exists.")
                    return@launch
                }

                val newUser = UserEntity(username = username, email = email, passwordHash = passwordHash)
                val userId = userRepository.registerUser(newUser)
                if (userId > 0) {
                    _registrationState.value = Response.Success(userId)
                } else {
                    _registrationState.value = Response.Error("Registration failed.")
                }
            } catch (e: Exception) {
                _registrationState.value = Response.Error("Error during registration: ${e.message}")
            }
        }
    }

    fun login(username: String, passwordPlain: String) {
        _loginState.value = Response.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val passwordHash = passwordPlain

                val user = userRepository.loginUser(username, passwordHash)
                if (user != null) {
                    userSessionManager.saveLoggedInUserId(user.id)
                    _loggedInUser.value = user
                    _loginState.value = Response.Success(user)
                } else {
                    _loginState.value = Response.Error("Invalid username or password.")
                }
            } catch (e: Exception) {
                _loginState.value = Response.Error("Error during login: ${e.message}")
            }
        }
    }

    fun logout() {
        viewModelScope.launch(Dispatchers.IO) {
            userSessionManager.clearSession()
            _loggedInUser.value = null
            _loginState.value = Response.Loading() // Reset login state
        }
    }

    fun resetRegistrationState() {
        _registrationState.value = Response.Loading()
    }

    fun resetLoginState() {
        _loginState.value = Response.Loading()
    }
}