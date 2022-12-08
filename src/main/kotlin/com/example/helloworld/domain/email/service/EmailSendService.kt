package com.example.helloworld.domain.email.service

import com.example.helloworld.domain.email.presentation.data.request.EmailSendRequestDto

interface EmailSendService {
    fun execute(emailSendRequestDto: EmailSendRequestDto)
}