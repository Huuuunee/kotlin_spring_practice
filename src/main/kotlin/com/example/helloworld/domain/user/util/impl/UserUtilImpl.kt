package com.example.helloworld.domain.user.util.impl

import com.example.helloworld.domain.user.domain.entity.User
import com.example.helloworld.domain.user.domain.repository.UserRepository
import com.example.helloworld.domain.user.exception.UserNotFoundException
import com.example.helloworld.domain.user.util.UserUtil
import com.example.helloworld.global.security.auth.AuthDetails
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service

@Service
class UserUtilImpl(
    private val userRepository: UserRepository
) : UserUtil {
    override fun fetchCurrentUser(): User {
        val principal = SecurityContextHolder.getContext().authentication.principal
        val email = if (principal is UserDetails) {
            (principal as AuthDetails).username
        } else {
            principal.toString()
        }
        return fetchUserByEmail(email)
    }

    override fun fetchUserByEmail(email: String): User =
        userRepository.findByEmail(email) ?: throw UserNotFoundException()
}