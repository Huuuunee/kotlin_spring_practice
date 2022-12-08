package com.example.helloworld.domain.email.presentation

import com.example.helloworld.domain.email.presentation.data.request.EmailSendRequestDto
import com.example.helloworld.domain.email.service.EmailSendService
import com.example.helloworld.domain.email.service.EmailVerifiedService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/email")
class EmailAuthController(
    private val emailSendService: EmailSendService,
    private val emailVerifiedService: EmailVerifiedService
) {

    @PostMapping
    fun emailSend(@RequestBody emailSentRequestDto: EmailSendRequestDto): ResponseEntity<Void> {
        emailSendService.execute(emailSentRequestDto)
        return ResponseEntity.noContent().build()
    }

    @GetMapping("/authentication")
    fun emailVerification(@RequestParam email: String, @RequestParam uuid: String): ResponseEntity<Void> {
        emailVerifiedService.execute(email, uuid)
        return ResponseEntity.noContent().build()
    }

}