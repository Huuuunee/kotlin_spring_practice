package com.example.helloworld.global.security.auth

import com.example.helloworld.domain.user.domain.repository.UserRepository
import com.example.helloworld.domain.user.exception.UserNotFoundException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class AuthDetailsService(
        private val userRepository: UserRepository
): UserDetailsService {
    override fun loadUserByUsername(email: String): UserDetails {
        val user = userRepository.findByEmail(email) ?: throw UserNotFoundException()
        return AuthDetails(user)
    }
}