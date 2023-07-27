package com.jwiltenburg.api.controllers.response

import java.time.LocalDate

data class UserResponse (
        val name: String,
        val uuid: String,
        val email: String,
        val dateOfBirth: LocalDate,
        val age: Int,
)