package com.example.helloworld.domain.post.service

import com.example.helloworld.domain.post.presentation.data.response.PostingListResponseDto
import org.springframework.data.domain.Pageable

interface GetPostInfoService {
    fun execute(pageable: Pageable): PostingListResponseDto
    fun execute(): PostingListResponseDto
}