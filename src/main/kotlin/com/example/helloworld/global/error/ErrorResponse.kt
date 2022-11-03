package com.example.helloworld.global.error

import org.springframework.http.HttpStatus


class ErrorResponse(errorCode: ErrorCode) {
    val code: Int
    val msg: String

    init{
        code = errorCode.code
        msg = errorCode.msg
    }
}