package com.jwiltenburg.api.validation

import com.jwiltenburg.api.services.UserService
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class EmailAvailableValidator(
        val userService: UserService
) : ConstraintValidator<EmailAvailable, String> {
    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        if(value.isNullOrEmpty()){
            return false
        }
        return userService.emailAvailable(value)
    }

}
