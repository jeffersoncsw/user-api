package com.jwiltenburg.api.controllers.request

import jakarta.validation.constraints.*
import java.time.LocalDate

data class UserUpdatePartRequest (

        var name: String?,

        @field:Email(message = "{email.validation}")
        var email: String?,

        @field:Past(message = "{dateofbirth.past}")
        var dateOfBirth: LocalDate?,

        @field:Min(1, message = "{age.min}")
        var age: Int?,
)