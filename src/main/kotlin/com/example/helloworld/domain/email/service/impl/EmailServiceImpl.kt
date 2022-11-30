package com.example.helloworld.domain.email.service.impl

import com.example.helloworld.domain.email.domain.entity.EmailAuthEntity
import com.example.helloworld.domain.email.exception.AuthExpiredException
import com.example.helloworld.domain.email.exception.ManyEmailAuthRequestException
import com.example.helloworld.domain.email.exception.MessageSendFailException
import com.example.helloworld.domain.email.presentation.dto.request.EmailSentRequestDto
import com.example.helloworld.domain.email.domain.repository.EmailAuthRepository
import com.example.helloworld.domain.email.service.EmailService
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*
import javax.mail.Message
import javax.mail.MessagingException

@Service
@EnableAsync
class EmailServiceImpl(
    private val mailSender: JavaMailSender,
    private val emailAuthRepository: EmailAuthRepository
): EmailService{

    @Transactional(rollbackFor = [Exception::class])
    override fun emailSend(emailSentRequestDto: EmailSentRequestDto) {

        val email: String = emailSentRequestDto.email
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

        if(authEntity.attemptCount >= 3) throw ManyEmailAuthRequestException()

        val updateAuthEntity: EmailAuthEntity = authEntity.resendEmailAuth(value)
        emailAuthRepository.save(updateAuthEntity)

        try {
            val message = mailSender.createMimeMessage()
            val msg =
                "<a href=\"http://localhost:8080/email/authentication?email=$email&uuid=$value\" style=\"padding: 10px; border: none; color: white; background-color: skyblue; border-radius: 8px; align-self: center; text-align: center;\">인증하기</a>"
            message.addRecipients(Message.RecipientType.TO, emailSentRequestDto.email)
            message.subject = "이메일 인증"
            message.setText(msg, "utf-8", "html")
            mailSender.send(message)
        } catch (ex: MessagingException) {
            throw MessageSendFailException()
        }

    }

    @Transactional(rollbackFor = [Exception::class])
    override fun emailVerified(email: String , uuid: String){

        val authEntity = emailAuthRepository.findById(email)
            .orElseThrow{ AuthExpiredException() }

        if(authEntity.randomValue != uuid) throw AuthExpiredException()

        val updateAuthEntity = authEntity.updateAuthentication(true)
        emailAuthRepository.save(updateAuthEntity)

    }

}