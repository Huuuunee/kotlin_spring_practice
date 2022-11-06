package com.example.helloworld.domain.email.service

import com.example.helloworld.domain.email.presentation.dto.request.EmailSentRequestDto

interface EmailService{
    fun emailSend(emailSentRequestDto: EmailSentRequestDto)
    fun emailVerified(email: String, uuid: String)
}