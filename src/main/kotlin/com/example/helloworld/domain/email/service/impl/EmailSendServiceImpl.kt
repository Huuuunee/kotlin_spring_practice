package com.example.helloworld.domain.email.service.impl

import com.example.helloworld.domain.email.domain.entity.EmailAuthEntity
import com.example.helloworld.domain.email.domain.repository.EmailAuthRepository
import com.example.helloworld.domain.email.exception.ManyEmailAuthRequestException
import com.example.helloworld.domain.email.presentation.dto.request.EmailSendRequestDto
import com.example.helloworld.domain.email.service.EmailSendService
import com.example.helloworld.global.email.EmailSender
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class EmailSendServiceImpl(
    private val emailSender: EmailSender,
    private val emailAuthRepository: EmailAuthRepository
) : EmailSendService {
    @Override
    @Transactional(rollbackFor = [Exception::class])
    override fun execute(emailSendRequestDto: EmailSendRequestDto) {

        val email: String = emailSendRequestDto.email
        val value: String = UUID.randomUUID().toString()
        val authEntity = emailAuthRepository.findById(email)
            .orElse(
                EmailAuthEntity(
                    authentication = false,
                    randomValue = value,
                    email = email,
                    attemptCount = 0
                )
            )

        if (authEntity.attemptCount >= 3) throw ManyEmailAuthRequestException()

        val updateAuthEntity: EmailAuthEntity = authEntity.resendEmailAuth(value)
        emailAuthRepository.save(updateAuthEntity)

        emailSender.send(email, value)
    }
}