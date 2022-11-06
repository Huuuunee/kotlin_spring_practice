package com.example.helloworld.domain.email.domain.repository

import com.example.helloworld.domain.email.domain.entity.EmailAuthEntity
import org.springframework.data.repository.CrudRepository

interface EmailAuthRepository: CrudRepository<EmailAuthEntity, String>