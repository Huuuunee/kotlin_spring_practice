package com.example.helloworld.domain.auth.service

import com.example.helloworld.domain.auth.presentation.dto.request.SignUpRequestDto

interface SignUpService {
    fun execute(signUpRequestDto: SignUpRequestDto)
}