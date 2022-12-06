package com.example.helloworld.domain.user.domain.entitiy

import com.example.helloworld.global.entity.BaseIdEntity
import javax.persistence.*

@Entity
@Table
class User(
    val email: String,

    val password: String,

    val name: String,
) : BaseIdEntity() {
}