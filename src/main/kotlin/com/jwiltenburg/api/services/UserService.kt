package com.jwiltenburg.api.services

import com.jwiltenburg.api.controllers.request.UserRequest
import com.jwiltenburg.api.entities.UserEntity

interface UserService {
    fun create(userRequest: UserRequest): UserEntity

}