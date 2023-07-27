package com.jwiltenburg.api.controllers.request

import java.time.LocalDate

data class UserUpdateRequest (
        var name: String?,
        var email: String?,
        var dateOfBirth: LocalDate?,
        var age: Int?,
)