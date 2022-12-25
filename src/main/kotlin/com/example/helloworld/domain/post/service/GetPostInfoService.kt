package com.example.helloworld.domain.post.service

import com.example.helloworld.domain.post.presentation.data.dto.GetPostInfoDto
import com.example.helloworld.domain.post.presentation.data.response.PostingListResponseDto
import com.example.helloworld.domain.post.presentation.data.response.PostingResponseDto
import org.springframework.data.domain.Pageable

interface GetPostInfoService {
    fun execute(pageable: Pageable): PostingListResponseDto
    fun execute(getPostInfoDto: GetPostInfoDto): PostingResponseDto
    fun execute(): PostingListResponseDto
}