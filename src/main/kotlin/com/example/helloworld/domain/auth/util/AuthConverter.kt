package com.example.helloworld.domain.auth.util

import com.example.helloworld.domain.auth.presentation.data.dto.RefreshTokenDto
import com.example.helloworld.domain.auth.presentation.data.request.SignInRequestDto
import com.example.helloworld.domain.auth.presentation.data.request.SignUpRequestDto
import com.example.helloworld.domain.auth.presentation.data.response.SignInResponseDto
import com.example.helloworld.domain.user.presentation.data.dto.UserDto

interface AuthConverter {

    fun toDto(signUpRequestDto: SignUpRequestDto): UserDto
    fun toDto(signInRequestDto: SignInRequestDto): UserDto
    fun toDto(refreshToken: String): RefreshTokenDto

}