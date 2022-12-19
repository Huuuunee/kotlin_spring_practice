package com.example.helloworld.domain.post.util.impl

import com.example.helloworld.domain.post.domain.entity.Post
import com.example.helloworld.domain.post.presentation.data.dto.CreatePostDto
import com.example.helloworld.domain.post.presentation.data.request.CreatePostRequestDto
import com.example.helloworld.domain.post.util.PostConverter
import com.example.helloworld.domain.user.domain.entity.User
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class PostConverterImpl() : PostConverter {
    override fun toDto(request: CreatePostRequestDto): CreatePostDto =
        CreatePostDto(request.title, request.content)

    override fun toEntity(createPostDto: CreatePostDto, userInfo: User): Post =
        Post(
            content = createPostDto.content,
            title = createPostDto.title,
            views = 0,
            localDateTime = LocalDateTime.now(),
            user = userInfo
        )
}