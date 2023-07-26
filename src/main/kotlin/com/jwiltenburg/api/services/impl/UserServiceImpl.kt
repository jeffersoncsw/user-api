package com.jwiltenburg.api.services.impl

import com.jwiltenburg.api.controllers.request.UserRequest
import com.jwiltenburg.api.entities.UserEntity
import com.jwiltenburg.api.extensions.toUserEntity
import com.jwiltenburg.api.repositories.UserRepository
import com.jwiltenburg.api.services.UserService
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
        val userRepository: UserRepository
) : UserService{
    override fun create(userRequest: UserRequest): UserEntity {
        return userRepository.save(userRequest.toUserEntity())
    }
}