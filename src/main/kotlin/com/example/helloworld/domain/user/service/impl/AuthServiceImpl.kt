package com.example.helloworld.domain.user.service.impl

import com.example.helloworld.domain.email.domain.entity.EmailAuthEntity
import com.example.helloworld.domain.email.domain.repository.EmailAuthRepository
import com.example.helloworld.domain.user.domain.entitiy.User
import com.example.helloworld.domain.user.domain.repository.UserRepository
import com.example.helloworld.domain.user.exception.EmailNotVerifiedException
import com.example.helloworld.domain.user.exception.UserNotFoundException
import com.example.helloworld.domain.user.presentation.dto.request.SignUpRequestDto
import com.example.helloworld.domain.user.service.AuthService
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@EnableAsync
class AuthServiceImpl(
    private val emailAuthRepository: EmailAuthRepository,
    private val passwordEncoder: PasswordEncoder,
    private val userRepository: UserRepository
): AuthService{

    //
    @Transactional(rollbackFor  = [Exception::class])
    override fun signUp(signUpRequestDto: SignUpRequestDto){
        val emailAuthEntity: EmailAuthEntity = emailAuthRepository.findById(signUpRequestDto.email)
            .orElseThrow{ UserNotFoundException() };

        //이메일 인증이 X
        if(!emailAuthEntity.authentication) throw EmailNotVerifiedException()

        if(userRepository.existsByEmail(signUpRequestDto.email)){
            throw UserNotFoundException()
        }
        val user = User(
            email = signUpRequestDto.email,
            password = passwordEncoder.encode(signUpRequestDto.password),
            name = signUpRequestDto.name,
        )
        userRepository.save(user)
    }

}