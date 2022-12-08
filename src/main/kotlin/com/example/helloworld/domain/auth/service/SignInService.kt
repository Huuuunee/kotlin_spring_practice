package com.example.helloworld.domain.auth.service

import com.example.helloworld.domain.auth.presentation.dto.request.SignInRequestDto
import com.example.helloworld.domain.auth.presentation.dto.response.SignInResponseDto

interface SignInService {
    fun execute(signInRequestDto: SignInRequestDto): SignInResponseDto
}