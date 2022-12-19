package com.example.helloworld.domain.post.domain.repository

import com.example.helloworld.domain.post.domain.entity.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<Post, Long> {
}