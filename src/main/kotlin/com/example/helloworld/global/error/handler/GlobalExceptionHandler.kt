package com.example.helloworld.global.error.handler

import com.example.helloworld.global.error.ErrorResponse
import com.example.helloworld.global.error.exception.BasicException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.net.BindException

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(BasicException::class)
    fun handleBaseException(ex: BasicException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(ex.errorCode);
        return ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.valueOf(errorResponse.code))
    }
}