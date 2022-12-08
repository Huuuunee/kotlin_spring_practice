package com.example.helloworld.domain.auth.presentation

import com.example.helloworld.domain.auth.presentation.data.request.SignInRequestDto
import com.example.helloworld.domain.auth.presentation.data.request.SignUpRequestDto
import com.example.helloworld.domain.auth.presentation.data.response.RefreshTokenResponseDto
import com.example.helloworld.domain.auth.presentation.data.response.SignInResponseDto
import com.example.helloworld.domain.auth.service.*
import com.example.helloworld.domain.auth.util.AuthConverter
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthController(
    private val signInService: SignInService,
    private val signUpService: SignUpService,
    private val getNewRefreshTokenService: GetNewRefreshTokenService,
    private val logOutService: LogOutService,
    private val authConverter: AuthConverter
) {

    @PostMapping("/signup")
    fun signUp(@RequestBody signUpRequestDto: SignUpRequestDto): ResponseEntity<Void> =
        authConverter.toDto(signUpRequestDto)
            .let { signUpService.execute(it) }
            .let { ResponseEntity.status(HttpStatus.OK).build() }

    @PostMapping
    fun signIn(@RequestBody signInRequestDto: SignInRequestDto): ResponseEntity<SignInResponseDto> =
        authConverter.toDto(signInRequestDto)
            .let { signInService.execute(it) }
            .let { ResponseEntity.ok(it) }

    @PatchMapping
    fun getNewRefreshToken(@RequestHeader("RefreshToken") refreshToken: String): ResponseEntity<RefreshTokenResponseDto> =
        authConverter.toDto(refreshToken)
            .let { getNewRefreshTokenService.execute(it) }
            .let { ResponseEntity.ok(it) }

    @DeleteMapping
    fun logout(): ResponseEntity<Void> {
        logOutService.execute()
        return ResponseEntity.ok().build()
    }

}