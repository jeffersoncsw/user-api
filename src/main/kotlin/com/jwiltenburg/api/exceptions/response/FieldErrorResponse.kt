package com.jwiltenburg.api.exceptions.response

data class FieldErrorResponse(
        val message: String,
        var field: String
)
