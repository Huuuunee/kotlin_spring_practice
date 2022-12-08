package com.example.helloworld.domain.auth.service

import com.example.helloworld.domain.auth.presentation.dto.response.RefreshTokenResponseDto

interface GetNewRefreshTokenService {
    fun execute(token: String): RefreshTokenResponseDto
}