package com.jwiltenburg.api.extensions

import com.jwiltenburg.api.controllers.request.UserRequest
import com.jwiltenburg.api.entities.UserEntity

fun UserRequest.toUserEntity(): UserEntity{
    return UserEntity(
            name = this.name,
            email = this.email,
            dateOfBirth = this.dateOfBirth,
            age = this.age)
}