package com.example.helloworld.domain.email.exception

import com.example.helloworld.global.error.ErrorCode
import com.example.helloworld.global.error.exception.BasicException

class EmailNotVerifiedException: BasicException(ErrorCode.EMAIL_NOT_VERIFIED)