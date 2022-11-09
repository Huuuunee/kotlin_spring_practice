package com.example.helloworld.domain.user.domain.repository

import com.example.helloworld.domain.user.domain.entitiy.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository: JpaRepository<User,Long>{
    fun findByEmail(email:String): User?
    fun existsByEmail(email:String): Boolean
}