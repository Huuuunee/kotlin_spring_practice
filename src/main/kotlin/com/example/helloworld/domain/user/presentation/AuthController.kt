package com.example.helloworld.domain.user.presentation

import com.example.helloworld.domain.user.presentation.dto.request.SignUpRequestDto
import com.example.helloworld.domain.user.service.AuthService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService,
) {

    @PostMapping("/signup")
    fun signUp(@RequestBody signUpRequestDto: SignUpRequestDto): ResponseEntity<Void>{

        //회원가입 함수
        authService.signUp(signUpRequestDto)
        return ResponseEntity.ok().build()
    }

}