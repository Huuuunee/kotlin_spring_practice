package com.example.helloworld.domain.auth.service.impl

import com.example.helloworld.domain.auth.domain.entity.RefreshToken
import com.example.helloworld.domain.auth.domain.repository.RefreshTokenRepository
import com.example.helloworld.domain.auth.exception.ExpiredRefreshTokenException
import com.example.helloworld.domain.auth.presentation.data.dto.RefreshTokenDto
import com.example.helloworld.domain.auth.presentation.data.response.RefreshTokenResponseDto
import com.example.helloworld.domain.auth.service.GetNewRefreshTokenService
import com.example.helloworld.global.security.exception.InvalidTokenException
import com.example.helloworld.global.security.jwt.JwtTokenProvider
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.ZonedDateTime

@Service
class GetNewRefreshTokenImpl(
    private val jwtTokenProvider: JwtTokenProvider,
    private val refreshTokenRepository: RefreshTokenRepository
) : GetNewRefreshTokenService {
    @Override
    @Transactional(rollbackFor = [Exception::class])
    override fun execute(refreshTokenDto: RefreshTokenDto): RefreshTokenResponseDto {
        val refresh = jwtTokenProvider.parseToken(refreshTokenDto.refreshToken) ?: throw InvalidTokenException()
        val email: String = jwtTokenProvider.exactEmailFromRefreshToken(refresh)
        val existingRefreshToken = refreshTokenRepository.findByToken(refresh) ?: throw ExpiredRefreshTokenException()
        val accessToken: String = jwtTokenProvider.generateAccessToken(email)
        val refreshToken: String = jwtTokenProvider.generateRefreshToken(email)
        val expiredAt: ZonedDateTime = jwtTokenProvider.accessExpiredTime

        val newRefreshToken = RefreshToken(
            existingRefreshToken.userId,
            refreshToken
        )

        refreshTokenRepository.save(newRefreshToken)

        return RefreshTokenResponseDto(accessToken, refreshToken, expiredAt)
    }
}