package com.example.helloworld.domain.auth.service

import com.example.helloworld.domain.auth.presentation.dto.response.RefreshTokenResponseDto
import com.example.helloworld.domain.auth.presentation.dto.response.SignInResponseDto
import com.example.helloworld.domain.auth.presentation.dto.request.SignInRequestDto
import com.example.helloworld.domain.auth.presentation.dto.request.SignUpRequestDto

interface AuthService {
    fun signUp(signUpRequestDto: SignUpRequestDto)
    fun signIn(signInRequestDto: SignInRequestDto): SignInResponseDto
    fun getNewRefreshToken(refresh: String): RefreshTokenResponseDto
    fun logOut()
}