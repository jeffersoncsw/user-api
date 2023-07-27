package com.jwiltenburg.api.controllers.request

import java.time.LocalDate
import java.util.UUID

data class UserRequest (
        val name: String,
        val uuid: String = UUID.randomUUID().toString(),
        val email: String,
        val dateOfBirth: LocalDate,
        val age: Integer,
)