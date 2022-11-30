package com.example.helloworld.domain.user.service.impl

import com.example.helloworld.domain.email.domain.repository.EmailAuthRepository
import com.example.helloworld.domain.user.domain.repository.UserRepository
import com.example.helloworld.domain.user.service.UserService
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.stereotype.Service

@Service
@EnableAsync
class UserServiceImpl(
): UserService {
}