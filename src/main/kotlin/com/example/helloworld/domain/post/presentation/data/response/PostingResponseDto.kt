package com.example.helloworld.domain.post.presentation.data.response

import com.example.helloworld.domain.post.domain.entity.Post
import java.time.LocalDateTime

class PostingResponseDto(
    val id: Long,
    val title: String,
    val content: String,
    val views: Long,
    val createDateTime: LocalDateTime,
    val userId: Long,
    val userName: String
) {
    constructor(post: Post) : this(
        id = post.id,
        title = post.title,
        content = post.content,
        views = post.views,
        createDateTime = post.createDateTime,
        userId = post.user.id,
        userName = post.user.name
    )
}