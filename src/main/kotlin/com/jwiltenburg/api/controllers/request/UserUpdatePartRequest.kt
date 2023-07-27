package com.jwiltenburg.api.controllers.request

import jakarta.validation.constraints.*
import java.time.LocalDate

data class UserUpdatePartRequest (

        var name: String?,

        @field:Email
        var email: String?,

        @field:Past
        var dateOfBirth: LocalDate?,

        @field:Min(1)
        var age: Int?,
)