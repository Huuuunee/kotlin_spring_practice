package com.example.helloworld.domain.post.exception

import com.example.helloworld.global.error.ErrorCode
import com.example.helloworld.global.error.exception.BasicException

class NotFoundPostingException : BasicException(ErrorCode.POSTING_NOT_FOUND) {
}