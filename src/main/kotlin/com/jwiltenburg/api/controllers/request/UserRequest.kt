package com.jwiltenburg.api.controllers.request

import java.time.LocalDate

data class UserRequest (
        val name: String,
        val email: String,
        val dateOfBirth: LocalDate,
        val age: Integer,
)