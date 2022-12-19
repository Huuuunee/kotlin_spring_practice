package com.example.helloworld.domain.post.service

import com.example.helloworld.domain.post.presentation.data.dto.CreatePostDto

interface CreatePostService {
    fun execute(createPostDto: CreatePostDto)
}