package com.example.helloworld.domain.auth.service.impl

import com.example.helloworld.domain.auth.domain.entity.RefreshToken
import com.example.helloworld.domain.auth.domain.repository.RefreshTokenRepository
import com.example.helloworld.domain.auth.exception.PasswordMismatchException
import com.example.helloworld.domain.auth.presentation.data.request.SignInRequestDto
import com.example.helloworld.domain.auth.presentation.data.response.SignInResponseDto
import com.example.helloworld.domain.auth.service.SignInService
import com.example.helloworld.domain.user.domain.entitiy.User
import com.example.helloworld.domain.user.domain.repository.UserRepository
import com.example.helloworld.domain.user.exception.UserNotFoundException
import com.example.helloworld.domain.user.presentation.data.dto.UserDto
import com.example.helloworld.global.security.jwt.JwtTokenProvider
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.ZonedDateTime

@Service
class SignInServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtTokenProvider: JwtTokenProvider,
    private val refreshTokenRepository: RefreshTokenRepository
) : SignInService {
    @Override
    @Transactional(rollbackFor = [Exception::class])
    override fun execute(userDto: UserDto): SignInResponseDto {
        val user: User = userRepository.findByEmail(userDto.email) ?: throw UserNotFoundException()
        if (!passwordEncoder.matches(user.password, user.password)) {
            throw PasswordMismatchException()
        }
        val accessToken: String = jwtTokenProvider.generateAccessToken(user.email)
        val refreshToken: String = jwtTokenProvider.generateRefreshToken(user.email)
        val expiredAt: ZonedDateTime = jwtTokenProvider.accessExpiredTime
        refreshTokenRepository.save(RefreshToken(user.id, refreshToken))
        return SignInResponseDto(accessToken, refreshToken, expiredAt)
    }
}