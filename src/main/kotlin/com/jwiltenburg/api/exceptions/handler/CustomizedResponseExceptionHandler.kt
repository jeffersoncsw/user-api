package com.jwiltenburg.api.exceptions.handler

import com.jwiltenburg.api.exceptions.NotFoundException
import com.jwiltenburg.api.exceptions.response.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class CustomizedResponseExceptionHandler {
    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(ex: NotFoundException, request: WebRequest) : ResponseEntity<ErrorResponse>{
        val erro = ErrorResponse(
                httpCode = HttpStatus.NOT_FOUND.value(),
                message = ex.message,
                internalCode = ex.errorCode,
                errors = null
        )

        return ResponseEntity(erro, HttpStatus.NOT_FOUND)
    }
}