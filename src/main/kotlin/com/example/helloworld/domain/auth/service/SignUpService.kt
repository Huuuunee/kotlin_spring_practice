package com.example.helloworld.domain.auth.service

import com.example.helloworld.domain.user.presentation.data.dto.UserDto

interface SignUpService {
    fun execute(userDto: UserDto)
}