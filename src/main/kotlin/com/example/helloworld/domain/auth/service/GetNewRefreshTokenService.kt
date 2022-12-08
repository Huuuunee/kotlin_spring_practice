package com.example.helloworld.domain.auth.service

import com.example.helloworld.domain.auth.presentation.data.dto.RefreshTokenDto
import com.example.helloworld.domain.auth.presentation.data.response.RefreshTokenResponseDto

interface GetNewRefreshTokenService {
    fun execute(refreshTokenDto: RefreshTokenDto): RefreshTokenResponseDto
}