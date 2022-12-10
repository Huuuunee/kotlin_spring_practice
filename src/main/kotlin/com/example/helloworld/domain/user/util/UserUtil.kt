package com.example.helloworld.domain.user.util

import com.example.helloworld.domain.user.domain.entity.User

interface UserUtil {
    fun fetchCurrentUser(): User

    fun fetchUserByEmail(email: String): User
}