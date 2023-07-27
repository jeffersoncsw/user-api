package com.jwiltenburg.api.services

import com.jwiltenburg.api.controllers.request.UserRequest
import com.jwiltenburg.api.controllers.response.UserResponse
import com.jwiltenburg.api.entities.UserEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface UserService {
    fun create(userRequest: UserRequest): UserEntity
    fun getAllUsers(page: Pageable): Page<UserResponse>

}