package com.example.helloworld.domain.auth.service.impl

import com.example.helloworld.domain.auth.domain.repository.RefreshTokenRepository
import com.example.helloworld.domain.auth.service.LogOutService
import com.example.helloworld.domain.user.domain.entitiy.User
import com.example.helloworld.domain.user.util.UserUtil
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LogOutServiceImpl(
    private val userUtil: UserUtil,
    private val refreshTokenRepository: RefreshTokenRepository
) : LogOutService {
    @Transactional(rollbackFor = [Exception::class])
    override fun execute() {
        val currentUser: User = userUtil.fetchCurrentUser()
        refreshTokenRepository.findByUserId(currentUser.id)?.let {
            refreshTokenRepository.delete(it)
        }
    }
}