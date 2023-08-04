package com.jwiltenburg.api.controllers.request

import com.jwiltenburg.api.validation.EmailAvailable
import jakarta.validation.constraints.*
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate
import java.util.*

data class UserRequest(

        @field:NotEmpty(message = "{name.notempty}")
        val name: String,

        val uuid: String = UUID.randomUUID().toString(),

        @field:Email(message = "{email.validation}")
        @field:NotEmpty(message = "{email.notempty}")
        @EmailAvailable
        val email: String,

        @field:NotNull(message = "{dateofbirth.notnull}")
        @field:DateTimeFormat(pattern = "yyyy-MM-dd")
        @field:Past(message = "{dateofbirth.past}")
        val dateOfBirth: LocalDate,

        @field:NotNull(message = "{age.notnull}")
        @field:Min(1, message = "{age.min}")
        val age: Int,

        @field:NotEmpty(message = "{password.notempty}")
        val password: String
)