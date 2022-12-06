package com.example.helloworld.global.error

import org.springframework.http.HttpStatus


class ErrorResponse(
    val code: Int,
    val msg: String
)