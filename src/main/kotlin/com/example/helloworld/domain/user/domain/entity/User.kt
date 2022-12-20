package com.example.helloworld.domain.user.domain.entity

import com.example.helloworld.domain.post.domain.entity.Post
import com.example.helloworld.global.entity.BaseIdEntity
import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table
class User(
    val email: String,
    val password: String,
    val name: String,
    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    val post: List<Post>?
) : BaseIdEntity() {
}