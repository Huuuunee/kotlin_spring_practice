package com.example.helloworld.domain.email.exception

import com.example.helloworld.global.error.ErrorCode
import com.example.helloworld.global.error.exception.BasicException

class MessageSendFailException: BasicException(ErrorCode.MAIL_SEND_FAIL) {
}