package com.example.helloworld.domain.user.exception

import com.example.helloworld.global.error.ErrorCode
import com.example.helloworld.global.error.exception.BasicException

class UserAlreadyException: BasicException(ErrorCode.USER_ALREADY) {
}