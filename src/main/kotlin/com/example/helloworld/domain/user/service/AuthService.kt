package com.example.helloworld.domain.user.service

import RefreshTokenResponseDto
import SignInResponseDto
import com.example.helloworld.domain.user.presentation.dto.request.SignInRequestDto
import com.example.helloworld.domain.user.presentation.dto.request.SignUpRequestDto

interface AuthService {
    fun signUp(signUpRequestDto: SignUpRequestDto)
    fun signIn(signInRequestDto: SignInRequestDto): SignInResponseDto
    fun getNewRefreshToken(refresh: String): RefreshTokenResponseDto
}