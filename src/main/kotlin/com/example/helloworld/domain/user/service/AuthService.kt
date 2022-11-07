package com.example.helloworld.domain.user.service

import com.example.helloworld.domain.user.presentation.dto.request.SignUpRequestDto

interface AuthService {
    fun signUp(signUpRequestDto: SignUpRequestDto)
}