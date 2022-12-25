package com.example.helloworld.domain.post.service.impl

import com.example.helloworld.domain.post.domain.entity.Post
import com.example.helloworld.domain.post.domain.repository.PostRepository
import com.example.helloworld.domain.post.exception.NotFoundPostingException
import com.example.helloworld.domain.post.presentation.data.dto.GetPostInfoDto
import com.example.helloworld.domain.post.presentation.data.response.PostingListResponseDto
import com.example.helloworld.domain.post.presentation.data.response.PostingResponseDto
import com.example.helloworld.domain.post.service.GetPostInfoService
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class GetPostInfoServiceImpl(
    private val postRepository: PostRepository
) : GetPostInfoService {

    override fun execute(): PostingListResponseDto =
        PostingListResponseDto(
            list = postRepository.findAll()
                .map { PostingResponseDto(it) }
        )

    override fun execute(pageable: Pageable): PostingListResponseDto =
        PostingListResponseDto(
            list = postRepository.findAll(pageable)
                .map { PostingResponseDto(it) }
                .content
        )

    override fun execute(getPostInfoDto: GetPostInfoDto): PostingResponseDto {
        val postInfo: Post = postRepository.findById(getPostInfoDto.postId)
            .orElseThrow { NotFoundPostingException() }
        val updatePostInfo: Post = postInfo.updateViews()
        postRepository.save(updatePostInfo)

        return PostingResponseDto(
            post = updatePostInfo
        )
    }
}