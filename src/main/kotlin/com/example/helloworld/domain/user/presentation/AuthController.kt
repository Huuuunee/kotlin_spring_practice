package com.example.helloworld.domain.user.presentation

import RefreshTokenResponseDto
import SignInResponseDto
import com.example.helloworld.domain.user.presentation.dto.request.SignInRequestDto
import com.example.helloworld.domain.user.presentation.dto.request.SignUpRequestDto
import com.example.helloworld.domain.user.service.AuthService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService,
) {

    @PostMapping("/signup")
    fun signUp(@RequestBody signUpRequestDto: SignUpRequestDto): ResponseEntity<Void>{
        authService.signUp(signUpRequestDto)
        return ResponseEntity.ok().build()
    }

    @PostMapping
    fun signIn(@RequestBody signInRequestDto: SignInRequestDto): ResponseEntity<SignInResponseDto> =
        ResponseEntity.ok(authService.signIn(signInRequestDto))

    @PatchMapping
    fun getNewRefreshToken(@RequestHeader("refreshToken") refresh: String): ResponseEntity<RefreshTokenResponseDto> =
        ResponseEntity.ok(authService.getNewRefreshToken(refresh))

}