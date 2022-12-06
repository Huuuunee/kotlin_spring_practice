package com.example.helloworld.domain.auth.domain.repository

import com.example.helloworld.domain.auth.domain.entity.RefreshToken
import org.springframework.data.repository.CrudRepository

interface RefreshTokenRepository : CrudRepository<RefreshToken, Long> {
    fun findByToken(token: String): RefreshToken?
    fun findByUserId(userId: Long): RefreshToken?
}