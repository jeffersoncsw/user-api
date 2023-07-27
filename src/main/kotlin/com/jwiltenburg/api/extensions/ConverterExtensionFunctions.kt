package com.jwiltenburg.api.extensions

import com.jwiltenburg.api.controllers.request.UserRequest
import com.jwiltenburg.api.controllers.request.UserUpdateRequest
import com.jwiltenburg.api.controllers.response.UserResponse
import com.jwiltenburg.api.entities.UserEntity

fun UserRequest.toUserEntity(): UserEntity{
    return UserEntity(
            name = this.name,
            uuid = this.uuid,
            email = this.email,
            dateOfBirth = this.dateOfBirth,
            age = this.age
    )
}

fun UserEntity.toUserResponse(): UserResponse{
    return UserResponse(
            name = this.name,
            uuid = this.uuid!!,
            email = this.email,
            dateOfBirth = this.dateOfBirth,
            age = this.age
    )
}

fun UserUpdateRequest.toUserEntity(previousValue: UserEntity): UserEntity{
    return UserEntity(
            id = previousValue.id,
            name = this.name ?: previousValue.name,
            uuid = previousValue.uuid,
            email = this.email ?: previousValue.email,
            dateOfBirth = this.dateOfBirth ?: previousValue.dateOfBirth,
            age = this.age ?: previousValue.age
    )
}