package com.example.helloworld.domain.auth.service.impl

import com.example.helloworld.domain.auth.presentation.dto.request.SignUpRequestDto
import com.example.helloworld.domain.auth.service.SignUpService
import com.example.helloworld.domain.email.domain.entity.EmailAuthEntity
import com.example.helloworld.domain.email.domain.repository.EmailAuthRepository
import com.example.helloworld.domain.email.exception.EmailNotVerifiedException
import com.example.helloworld.domain.user.domain.entitiy.User
import com.example.helloworld.domain.user.domain.repository.UserRepository
import com.example.helloworld.domain.user.exception.UserAlreadyException
import com.example.helloworld.domain.user.exception.UserNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SignUpServiceImpl(
    private val emailAuthRepository: EmailAuthRepository,
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) : SignUpService {
    @Override
    @Transactional(rollbackFor = [Exception::class])
    override fun execute(signUpRequestDto: SignUpRequestDto) {
        val emailAuthEntity: EmailAuthEntity = emailAuthRepository.findById(signUpRequestDto.email)
            .orElseThrow { UserNotFoundException() };

        //이메일 인증이 X
        if (!emailAuthEntity.authentication) throw EmailNotVerifiedException()

        if (userRepository.existsByEmail(signUpRequestDto.email)) { // 회원가입 할때 이메일 있으면 안되니까
            throw UserAlreadyException()
        }
        val user = User(
            email = signUpRequestDto.email,
            password = passwordEncoder.encode(signUpRequestDto.password),
            name = signUpRequestDto.name,
        )
        userRepository.save(user)
    }

}