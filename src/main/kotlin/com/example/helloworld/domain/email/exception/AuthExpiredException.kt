package com.example.helloworld.domain.email.exception

import com.example.helloworld.global.error.ErrorCode
import com.example.helloworld.global.error.exception.BasicException

class AuthExpiredException : BasicException(ErrorCode.AUTH_CODE_EXPIRED) {
}