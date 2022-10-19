package com.example.helloworld.global.exception

import org.springframework.http.HttpStatus


class ErrorResponse(errorCode: ErrorCode) {
    val status: HttpStatus
    val message: String

    init{
        status = errorCode.status
        message = errorCode.message
    }
}