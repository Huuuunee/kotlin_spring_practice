package com.example.helloworld.domain.user.service

import com.example.helloworld.domain.user.domain.entitiy.User
import com.example.helloworld.domain.user.domain.repository.UserRepository
import com.example.helloworld.domain.user.presentation.dto.request.SaveUserRequestDto
import org.springframework.stereotype.Service

import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
        private val userRepository: UserRepository
){
    @Transactional(rollbackFor  = [Exception::class])
    fun signin(saveUserRequestDto: SaveUserRequestDto){
        val user = User(
                email = saveUserRequestDto.email,
                password = saveUserRequestDto.password,
                name = saveUserRequestDto.name,
        )
        userRepository.save(user)
    }
}