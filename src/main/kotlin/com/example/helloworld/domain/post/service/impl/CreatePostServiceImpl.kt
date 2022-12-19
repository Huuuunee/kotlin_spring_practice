package com.example.helloworld.domain.post.service.impl

import com.example.helloworld.domain.post.domain.entity.Post
import com.example.helloworld.domain.post.domain.repository.PostRepository
import com.example.helloworld.domain.post.presentation.data.dto.CreatePostDto
import com.example.helloworld.domain.post.service.CreatePostService
import com.example.helloworld.domain.post.util.PostConverter
import com.example.helloworld.domain.user.domain.entity.User
import com.example.helloworld.domain.user.util.UserUtil
import org.springframework.stereotype.Service

@Service
class CreatePostServiceImpl(
    private val userUtil: UserUtil,
    private val postConverter: PostConverter,
    private val postRepository: PostRepository
) : CreatePostService {

    @Override
    override fun execute(createPostDto: CreatePostDto) {
        val userInfo: User = userUtil.fetchCurrentUser()
        val postInfo: Post = postConverter.toEntity(createPostDto, userInfo)
        postRepository.save(postInfo)
    }

}