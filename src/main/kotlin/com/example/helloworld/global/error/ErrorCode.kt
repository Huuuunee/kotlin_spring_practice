package com.example.helloworld.global.error

import org.springframework.http.HttpStatus

enum class ErrorCode(
        val code: Int,
        val msg: String
        ) {
    UNAUTHORIZED(401,"인증되지 않았습니다"),
    EXPIRED_TOKEN(401,"만료된 토큰입니다"),
    USER_NOT_FOUND(404,"존재하지 않는 유저입니다"),
    INTERNAL_SERVER_ERROR(500,"알지못하는 에러입니다")
}