package com.example.helloworld.global.exception.exceptionCollection

import com.example.helloworld.global.exception.ErrorCode

class BaseException(val errorCode: ErrorCode): RuntimeException() {
}