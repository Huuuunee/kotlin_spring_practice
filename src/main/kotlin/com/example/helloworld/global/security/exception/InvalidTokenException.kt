package com.example.helloworld.global.security.exception

import com.example.helloworld.global.error.ErrorCode
import com.example.helloworld.global.error.exception.BasicException

class InvalidTokenException: BasicException(ErrorCode.UNAUTHORIZED) {
}