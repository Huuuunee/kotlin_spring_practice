package com.example.helloworld.global.error.handler

import com.example.helloworld.global.error.ErrorCode
import com.example.helloworld.global.error.ErrorResponse
import com.example.helloworld.global.error.exception.BasicException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.servlet.http.HttpServletRequest

@RestControllerAdvice
class GlobalExceptionHandler(
) {

    private val log = LoggerFactory.getLogger(this.javaClass.simpleName)

    @ExceptionHandler(BasicException::class)
    fun globalExceptionHandler(request: HttpServletRequest, e: BasicException): ResponseEntity<ErrorResponse?> {
        log.error(request.requestURL.toString())
        log.error(e.errorCode.msg)
        val errorCode: ErrorCode = e.errorCode
        return ResponseEntity(
            ErrorResponse(code = errorCode.code, msg = errorCode.msg),
            HttpStatus.valueOf(errorCode.code)
        )
    }
}