package com.ilham.event.data.repository

import com.ilham.event.data.local.dao.UserDao
import com.ilham.event.data.local.entity.UserEntity
import jakarta.inject.Inject

class UserRepository @Inject constructor(private val userDao: UserDao) {
    suspend fun registerUser(user: UserEntity): Long {
        return userDao.insertUser(user)
    }

    suspend fun loginUser(username: String, passwordHash: String): UserEntity? {
        val user = userDao.getUserByUsername(username)
        return if (user != null && user.passwordHash == passwordHash) user else null
    }

    suspend fun getUserById(userId: Long): UserEntity? {
        return userDao.getUserById(userId)
    }

    suspend fun getUserByUsername(username: String): UserEntity? {
        return userDao.getUserByUsername(username)
    }

    suspend fun getUserByEmail(email: String): UserEntity? {
        return userDao.getUserByEmail(email)
    }
}