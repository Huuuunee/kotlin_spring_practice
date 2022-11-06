package com.example.helloworld.domain.email.exception

import com.example.helloworld.global.error.ErrorCode
import com.example.helloworld.global.error.exception.BasicException

class ManyEmailAuthRequestException: BasicException(ErrorCode.MANY_EMAIL_AUTH_REQUEST) {
}