package com.example.helloworld.domain.post.presentation

import com.example.helloworld.domain.post.presentation.data.request.CreatePostRequestDto
import com.example.helloworld.domain.post.service.CreatePostService
import com.example.helloworld.domain.post.util.PostConverter
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/posting")
class PostController(
    private val postConverter: PostConverter,
    private val createPostService: CreatePostService
) {

    @PostMapping
    fun createPost(@RequestBody createPostRequestDto: CreatePostRequestDto): ResponseEntity<Void> =
        postConverter.toDto(createPostRequestDto)
            .let { createPostService.execute(it) }
            .let { ResponseEntity.status(HttpStatus.CREATED).build() }

}