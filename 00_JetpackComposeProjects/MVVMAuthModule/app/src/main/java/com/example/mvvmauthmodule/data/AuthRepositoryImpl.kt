package com.example.mvvmauthmodule.data

import com.example.mvvmauthmodule.domain.repository.AuthRepository
import kotlinx.coroutines.delay

// Auth Repository Implementation
class AuthRepositoryImpl : AuthRepository {
    override suspend fun login(email: String, password: String): Boolean {
        delay(2000)
        return true
    }

    override suspend fun register(email: String, password: String): Boolean {
        delay(2000)
        return true
    }
}