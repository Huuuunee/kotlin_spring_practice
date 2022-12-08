package com.example.helloworld.domain.auth.service

import com.example.helloworld.domain.auth.presentation.data.request.SignInRequestDto
import com.example.helloworld.domain.auth.presentation.data.response.SignInResponseDto
import com.example.helloworld.domain.user.presentation.data.dto.UserDto

interface SignInService {
    fun execute(userDto: UserDto): SignInResponseDto
}