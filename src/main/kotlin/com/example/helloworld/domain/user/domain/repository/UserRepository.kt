package com.example.helloworld.domain.user.domain.repository

import com.example.helloworld.domain.user.domain.entitiy.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository:JpaRepository<User,Long>{
}