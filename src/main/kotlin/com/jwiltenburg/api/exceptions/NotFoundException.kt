package com.jwiltenburg.api.exceptions

class NotFoundException(
        override val message: String,
        val errorCode: String
) : Exception()