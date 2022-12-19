package com.example.helloworld.domain.post.util

import com.example.helloworld.domain.post.domain.entity.Post
import com.example.helloworld.domain.post.presentation.data.dto.CreatePostDto
import com.example.helloworld.domain.post.presentation.data.request.CreatePostRequestDto
import com.example.helloworld.domain.user.domain.entity.User

interface PostConverter {
    fun toDto(request: CreatePostRequestDto): CreatePostDto
    fun toEntity(createPostDto: CreatePostDto, userInfo: User): Post
}