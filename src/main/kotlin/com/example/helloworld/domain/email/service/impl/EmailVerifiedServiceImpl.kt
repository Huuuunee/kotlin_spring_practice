package com.example.helloworld.domain.email.service.impl

import com.example.helloworld.domain.email.domain.repository.EmailAuthRepository
import com.example.helloworld.domain.email.exception.AuthExpiredException
import com.example.helloworld.domain.email.service.EmailVerifiedService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class EmailVerifiedServiceImpl(
    private val emailAuthRepository: EmailAuthRepository
) : EmailVerifiedService {
    @Override
    @Transactional(rollbackFor = [Exception::class])
    override fun execute(email: String, uuid: String) {

        val authEntity = emailAuthRepository.findById(email)
            .orElseThrow { AuthExpiredException() }

        if (authEntity.randomValue != uuid) throw AuthExpiredException()

        val updateAuthEntity = authEntity.updateAuthentication(true)
        emailAuthRepository.save(updateAuthEntity)

    }
}