package com.example.helloworld.global.exception

import org.springframework.http.HttpStatus

enum class ErrorCode(val status: HttpStatus, val message: String) {
    OK(HttpStatus.OK,"요청 성공");
}