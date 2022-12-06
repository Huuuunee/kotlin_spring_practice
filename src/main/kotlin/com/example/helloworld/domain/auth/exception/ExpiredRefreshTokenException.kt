package com.example.helloworld.domain.auth.exception

import com.example.helloworld.global.error.ErrorCode
import com.example.helloworld.global.error.exception.BasicException

class ExpiredRefreshTokenException : BasicException(ErrorCode.EXPIRED_TOKEN) {
}