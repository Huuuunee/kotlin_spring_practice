package com.example.helloworld.domain.user.domain.entitiy

import com.example.helloworld.global.entity.BaseIdEntity
import javax.persistence.*

@Entity
@Table
class User(
        var email: String,
        var password : String,
        var name : String,
        @Column(nullable = true, name = "refresh_token")
        var refreshToken: String? = null
) : BaseIdEntity(){
        fun updateToken(refreshToken: String) {
                this.refreshToken = refreshToken
        }
}