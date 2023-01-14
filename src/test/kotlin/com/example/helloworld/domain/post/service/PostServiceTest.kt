package com.example.helloworld.domain.post.service

import com.example.helloworld.domain.post.domain.entity.Post
import com.example.helloworld.domain.post.domain.repository.PostRepository
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.mockk

class PostServiceTest : BehaviorSpec({
    val postRepository: PostRepository = mockk()

    Given("게시글의 정보가 주어질 떄") {
        When("게시글 생성을 할 떄") {
            val postInfo = mockk<Post>()
            val save = postRepository.save(postInfo)

            Then("생성에 성공하면 생성한다") {
            }

            Then("생성에 실패하면 에러처리한다") {
                throw RuntimeException()
            }
        }
    }

})