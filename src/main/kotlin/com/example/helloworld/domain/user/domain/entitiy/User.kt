package com.example.helloworld.domain.user.domain.entitiy

import com.example.helloworld.global.entity.BaseIdEntity
import javax.persistence.*

@Entity
@Table
class User(
    val email: String,

    val password: String,

    val name: String,

    @Column(nullable = true, name = "refresh_token")
    val refreshToken: String? = null
) : BaseIdEntity() {
    fun updateRefreshToken(refreshToken: String): User {
        val user = User(
            email = this.email,
            password = this.password,
            refreshToken = refreshToken,
            name = this.name
        )
        user.id = this.id
        return user
    }
}