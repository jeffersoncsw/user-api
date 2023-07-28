package com.jwiltenburg.api.services

import com.jwiltenburg.api.controllers.request.UserRequest
import com.jwiltenburg.api.controllers.request.UserUpdatePartRequest
import com.jwiltenburg.api.controllers.request.UserUpdateRequest
import com.jwiltenburg.api.controllers.response.UserResponse
import com.jwiltenburg.api.entities.UserEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface UserService {
    fun create(userRequest: UserRequest): UserEntity
    fun getAllUsers(page: Pageable): Page<UserResponse>
    fun findByNameUser(name: String): List<UserResponse>
    fun updateUser(uuid: String, userUpdateRequest: UserUpdateRequest)
    fun updatePartUser(uuid: String, userUpdatePartRequest: UserUpdatePartRequest)
    fun deleteUser(uuid: String)
    fun emailAvailable(email: String): Boolean

}