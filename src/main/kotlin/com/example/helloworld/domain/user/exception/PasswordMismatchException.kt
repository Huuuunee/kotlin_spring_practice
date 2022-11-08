package com.example.helloworld.domain.user.exception

import com.example.helloworld.global.error.ErrorCode
import com.example.helloworld.global.error.exception.BasicException

class PasswordMismatchException: BasicException(ErrorCode.PASSWORD_MISMATCH) {
}