package com.example.helloworld.domain.auth.presentation

import com.example.helloworld.domain.auth.presentation.dto.response.RefreshTokenResponseDto
import com.example.helloworld.domain.auth.presentation.dto.response.SignInResponseDto
import com.example.helloworld.domain.auth.presentation.dto.request.SignInRequestDto
import com.example.helloworld.domain.auth.presentation.dto.request.SignUpRequestDto
import com.example.helloworld.domain.auth.service.AuthService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
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
    fun signUp(@RequestBody signUpRequestDto: SignUpRequestDto): ResponseEntity<Void> {
        authService.signUp(signUpRequestDto)
        return ResponseEntity.ok().build()
    }

    @PostMapping
    fun signIn(@RequestBody signInRequestDto: SignInRequestDto): ResponseEntity<SignInResponseDto> =
        ResponseEntity.ok(authService.signIn(signInRequestDto))

    @PatchMapping
    fun getNewRefreshToken(@RequestHeader("RefreshToken") token: String): ResponseEntity<RefreshTokenResponseDto> =
        ResponseEntity.ok(authService.getNewRefreshToken(token))

    @DeleteMapping
    fun logout(): ResponseEntity<Void> {
        authService.logOut()
        return ResponseEntity.ok().build()
    }

}