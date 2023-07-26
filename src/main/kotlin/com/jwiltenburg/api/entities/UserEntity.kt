package com.jwiltenburg.api.entities

import java.time.LocalDate

data class UserEntity (
        val id: String,
        val name: String,
        val email: String,
        val dateOfBirth: LocalDate,
        val age: Integer,
)