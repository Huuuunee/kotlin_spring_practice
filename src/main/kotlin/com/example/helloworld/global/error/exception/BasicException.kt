package com.example.helloworld.global.error.exception

import com.example.helloworld.global.error.ErrorCode

open class BasicException(val errorCode: ErrorCode): RuntimeException() {
}