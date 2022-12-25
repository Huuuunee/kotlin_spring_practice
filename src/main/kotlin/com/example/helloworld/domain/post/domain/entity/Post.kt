package com.example.helloworld.domain.post.domain.entity

import com.example.helloworld.domain.user.domain.entity.User
import com.example.helloworld.global.entity.BaseIdEntity
import com.fasterxml.jackson.annotation.JsonIgnore
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table
class Post(
    @Column(nullable = false, length = 100)
    val title: String,
    @Column(nullable = false, length = 10000)
    val content: String,
    val views: Long = 0,
    val createDateTime: LocalDateTime,
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User
) : BaseIdEntity() {
    fun updateViews(): Post {
        val post = Post(
            title = this.title,
            content = this.content,
            views = this.views + 1,
            createDateTime = this.createDateTime,
            user = this.user
        )
        post.id = this.id
        return post
    }
}