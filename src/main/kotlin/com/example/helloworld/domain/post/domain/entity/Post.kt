package com.example.helloworld.domain.post.domain.entity

import com.example.helloworld.domain.user.domain.entity.User
import com.example.helloworld.global.entity.BaseIdEntity
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table
class Post(
    @Column(nullable = false, length = 10000)
    val content: String,
    @Column(nullable = false, length = 100)
    val title: String,
    val views: Long = 0,
    val localDateTime: LocalDateTime,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User
) : BaseIdEntity(
)