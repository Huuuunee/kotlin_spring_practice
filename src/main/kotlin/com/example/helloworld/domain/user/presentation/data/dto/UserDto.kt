package com.example.helloworld.domain.user.presentation.data.dto

data class UserDto(
    val userId: Long,
    val email: String,
    val password: String,
    val name: String
)
