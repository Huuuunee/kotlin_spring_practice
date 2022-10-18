package com.example.helloworld.global.exception.handler

import com.example.helloworld.global.exception.ErrorResponse
import com.example.helloworld.global.exception.exceptionCollection.BaseException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(BaseException::class)
    fun handleBaseException(ex: BaseException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(ex.errorCode);
        return ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.valueOf(errorResponse.status.value()))
    }
}