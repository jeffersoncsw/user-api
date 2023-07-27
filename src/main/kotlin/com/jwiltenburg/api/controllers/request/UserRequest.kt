package com.jwiltenburg.api.controllers.request

import jakarta.validation.constraints.*
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate
import java.util.*

data class UserRequest (

        @field:NotEmpty
        val name: String,

        val uuid: String = UUID.randomUUID().toString(),

        @field:Email
        @field:NotEmpty
        val email: String,

        @field:NotNull
        @field:DateTimeFormat(pattern = "yyyy-MM-dd")
        @field:Past
        val dateOfBirth: LocalDate,

        @field:NotNull
        @field:Min(1)
        val age: Int,
)