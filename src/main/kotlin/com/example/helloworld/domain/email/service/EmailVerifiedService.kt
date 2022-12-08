package com.example.helloworld.domain.email.service

interface EmailVerifiedService {
    fun execute(email: String, key: String)
}