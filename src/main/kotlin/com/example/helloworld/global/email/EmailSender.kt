package com.example.helloworld.global.email

import com.example.helloworld.domain.email.exception.MessageSendFailException
import org.slf4j.LoggerFactory
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Component
import javax.mail.Message
import javax.mail.MessagingException

@Component
class EmailSender(
    private val mailSender: JavaMailSender,
) {
    fun send(email: String?, value: String) {
        try {
            val message = mailSender.createMimeMessage()
            val msg =
                "<a href=\"http://localhost:8080/email/authentication?email=$email&uuid=$value\" style=\"padding: 10px; border: none; color: white; background-color: skyblue; border-radius: 8px; align-self: center; text-align: center;\">인증하기</a>"
            message.addRecipients(Message.RecipientType.TO, email)
            message.subject = "이메일 인증"
            message.setText(msg, "utf-8", "html")
            mailSender.send(message)
        } catch (ex: MessagingException) {
            throw MessageSendFailException()
        }
    }

}