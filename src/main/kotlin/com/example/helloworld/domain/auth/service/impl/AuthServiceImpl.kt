package com.example.helloworld.domain.auth.service.impl

import com.example.helloworld.domain.auth.presentation.dto.response.RefreshTokenResponseDto
import com.example.helloworld.domain.auth.presentation.dto.response.SignInResponseDto
import com.example.helloworld.domain.auth.domain.entity.RefreshToken
import com.example.helloworld.domain.auth.domain.repository.RefreshTokenRepository
import com.example.helloworld.domain.auth.exception.ExpiredRefreshTokenException
import com.example.helloworld.domain.email.domain.entity.EmailAuthEntity
import com.example.helloworld.domain.email.domain.repository.EmailAuthRepository
import com.example.helloworld.domain.user.domain.entitiy.User
import com.example.helloworld.domain.user.domain.repository.UserRepository
import com.example.helloworld.domain.email.exception.EmailNotVerifiedException
import com.example.helloworld.domain.auth.exception.PasswordMismatchException
import com.example.helloworld.domain.user.exception.UserAlreadyException
import com.example.helloworld.domain.user.exception.UserNotFoundException
import com.example.helloworld.domain.auth.presentation.dto.request.SignInRequestDto
import com.example.helloworld.domain.auth.presentation.dto.request.SignUpRequestDto
import com.example.helloworld.domain.auth.service.AuthService
import com.example.helloworld.domain.user.util.UserUtil
import com.example.helloworld.global.security.exception.InvalidTokenException
import com.example.helloworld.global.security.jwt.JwtTokenProvider
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.ZonedDateTime

@Service
@EnableAsync
class AuthServiceImpl(
    private val emailAuthRepository: EmailAuthRepository,
    private val passwordEncoder: PasswordEncoder,
    private val userRepository: UserRepository,
    private val refreshTokenRepository: RefreshTokenRepository,
    private val jwtTokenProvider: JwtTokenProvider,
    private val userUtil: UserUtil
) : AuthService {

    // 회원가입 기능
    @Transactional(rollbackFor = [Exception::class])
    override fun signUp(signUpRequestDto: SignUpRequestDto) {
        val emailAuthEntity: EmailAuthEntity = emailAuthRepository.findById(signUpRequestDto.email)
            .orElseThrow { UserNotFoundException() };

        //이메일 인증이 X
        if (!emailAuthEntity.authentication) throw EmailNotVerifiedException()

        if (userRepository.existsByEmail(signUpRequestDto.email)) { // 회원가입 할때 이메일 있으면 안되니까
            throw UserAlreadyException()
        }
        val user = User(
            email = signUpRequestDto.email,
            password = passwordEncoder.encode(signUpRequestDto.password),
            name = signUpRequestDto.name,
        )
        userRepository.save(user)
    }

    @Transactional(rollbackFor = [Exception::class])
    override fun signIn(signInRequestDto: SignInRequestDto): SignInResponseDto {
        val user: User = userRepository.findByEmail(signInRequestDto.email) ?: throw UserNotFoundException()
        if (!passwordEncoder.matches(signInRequestDto.password, user.password)) {
            throw PasswordMismatchException()
        }
        val accessToken: String = jwtTokenProvider.generateAccessToken(signInRequestDto.email)
        val refreshToken: String = jwtTokenProvider.generateRefreshToken(signInRequestDto.email)
        val expiredAt: ZonedDateTime = jwtTokenProvider.accessExpiredTime
        refreshTokenRepository.save(RefreshToken(user.id, refreshToken))
        return SignInResponseDto(accessToken, refreshToken, expiredAt)
    }

    @Transactional(rollbackFor = [Exception::class])
    override fun getNewRefreshToken(token: String): RefreshTokenResponseDto {
        val refresh = jwtTokenProvider.parseToken(token) ?: throw InvalidTokenException()
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

    @Transactional(rollbackFor = [Exception::class])
    override fun logOut() {
        val currentUser: User = userUtil.fetchCurrentUser()
        refreshTokenRepository.findByUserId(currentUser.id)?.let {
            refreshTokenRepository.delete(it)
        }
    }
}