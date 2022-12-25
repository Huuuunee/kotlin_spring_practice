package com.example.helloworld.domain.post.presentation

import com.example.helloworld.domain.post.presentation.data.request.CreatePostRequestDto
import com.example.helloworld.domain.post.presentation.data.request.GetPostInfoRequestDto
import com.example.helloworld.domain.post.presentation.data.response.PostingListResponseDto
import com.example.helloworld.domain.post.presentation.data.response.PostingResponseDto
import com.example.helloworld.domain.post.service.CreatePostService
import com.example.helloworld.domain.post.service.GetPostInfoService
import com.example.helloworld.domain.post.util.PostConverter
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/posting")
class PostController(
    private val postConverter: PostConverter,
    private val createPostService: CreatePostService,
    private val getPostInfoService: GetPostInfoService
) {

    @PostMapping
    fun createPost(@RequestBody createPostRequestDto: CreatePostRequestDto): ResponseEntity<Void> =
        postConverter.toDto(createPostRequestDto)
            .let { createPostService.execute(it) }
            .let { ResponseEntity.status(HttpStatus.CREATED).build() }

    @GetMapping("/list")
    fun getAllPostInfo(pageable: Pageable): ResponseEntity<PostingListResponseDto> =
        ResponseEntity.ok(getPostInfoService.execute(pageable))

    @GetMapping
    fun getAllPostInfo(): ResponseEntity<PostingListResponseDto> =
        ResponseEntity.ok(getPostInfoService.execute())

    @GetMapping("/{postId}")
    fun getPostInfo(@PathVariable("postId") postId: Long): ResponseEntity<PostingResponseDto> =
        postConverter.toDto(postId)
            .let { getPostInfoService.execute(it) }
            .let { ResponseEntity.ok(it) }

}