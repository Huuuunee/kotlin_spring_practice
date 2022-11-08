package com.example.helloworld.domain.user.service.impl

import SignInResponseDto
import com.example.helloworld.domain.email.domain.entity.EmailAuthEntity
import com.example.helloworld.domain.email.domain.repository.EmailAuthRepository
import com.example.helloworld.domain.user.domain.entitiy.User
import com.example.helloworld.domain.user.domain.repository.UserRepository
import com.example.helloworld.domain.user.exception.EmailNotVerifiedException
import com.example.helloworld.domain.user.exception.PasswordMismatchException
import com.example.helloworld.domain.user.exception.UserAlreadyException
import com.example.helloworld.domain.user.exception.UserNotFoundException
import com.example.helloworld.domain.user.presentation.dto.request.SignInRequestDto
import com.example.helloworld.domain.user.presentation.dto.request.SignUpRequestDto
import com.example.helloworld.domain.user.service.AuthService
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
    private val jwtTokenProvider: JwtTokenProvider,

    ): AuthService{

    // 회원가입 기능
    @Transactional(rollbackFor  = [Exception::class])
    override fun signUp(signUpRequestDto: SignUpRequestDto){
        val emailAuthEntity: EmailAuthEntity = emailAuthRepository.findById(signUpRequestDto.email)
            .orElseThrow{ UserNotFoundException() };

        //이메일 인증이 X
        if(!emailAuthEntity.authentication) throw EmailNotVerifiedException()

        if(userRepository.existsByEmail(signUpRequestDto.email)){ // 회원가입 할때 이메일 있으면 안되니까
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

        if(!passwordEncoder.matches(signInRequestDto.password,user.password)){
            throw PasswordMismatchException()
        }
        val accessToken: String = jwtTokenProvider.generateAccessToken(signInRequestDto.email)
        val refreshToken: String = jwtTokenProvider.generateRefreshToken(signInRequestDto.email)
        val expiredAt: ZonedDateTime = jwtTokenProvider.accessExpiredTime

        user.updateToken(refreshToken)

        return SignInResponseDto(accessToken,refreshToken,expiredAt)
    }
}