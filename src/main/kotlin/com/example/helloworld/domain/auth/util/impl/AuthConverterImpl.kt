package com.example.helloworld.domain.auth.util.impl

import com.example.helloworld.domain.auth.presentation.data.dto.RefreshTokenDto
import com.example.helloworld.domain.auth.presentation.data.request.SignInRequestDto
import com.example.helloworld.domain.auth.presentation.data.request.SignUpRequestDto
import com.example.helloworld.domain.auth.util.AuthConverter
import com.example.helloworld.domain.user.presentation.data.dto.UserDto
import org.springframework.stereotype.Component

@Component
class AuthConverterImpl : AuthConverter {
    override fun toDto(signUpRequestDto: SignUpRequestDto): UserDto =
        UserDto(-1, signUpRequestDto.email, signUpRequestDto.password, signUpRequestDto.name)

    override fun toDto(signInRequestDto: SignInRequestDto): UserDto =
        UserDto(-1, signInRequestDto.email, signInRequestDto.password, "")

    override fun toDto(refreshToken: String): RefreshTokenDto =
        RefreshTokenDto(refreshToken)

}