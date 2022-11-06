package com.example.helloworld.global.config.mail.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "spring.mail")
class MailProperties(
    val host: String,
    val port: Int,
    val username: String,
    val password: String
) {
}