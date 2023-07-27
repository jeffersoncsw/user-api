package com.jwiltenburg.api.services.impl

import com.jwiltenburg.api.controllers.request.UserRequest
import com.jwiltenburg.api.controllers.request.UserUpdateRequest
import com.jwiltenburg.api.controllers.response.UserResponse
import com.jwiltenburg.api.entities.UserEntity
import com.jwiltenburg.api.exceptions.NotFoundException
import com.jwiltenburg.api.extensions.toUserEntity
import com.jwiltenburg.api.extensions.toUserResponse
import com.jwiltenburg.api.repositories.UserRepository
import com.jwiltenburg.api.services.UserService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
        val userRepository: UserRepository
) : UserService{
    override fun create(userRequest: UserRequest): UserEntity {
        return userRepository.save(userRequest.toUserEntity())
    }

    override fun getAllUsers(page: Pageable): Page<UserResponse> {
        val pages = userRepository.findAll(page)
        if(pages.isEmpty){
            throw NotFoundException(message = "There is no resource registered in the database", errorCode = "U-0001")
        }
        return pages.map { it.toUserResponse() }
    }

    override fun findByNameUser(name: String?): List<UserResponse> {
        val existName = name?.let { userRepository.findByNameContainingIgnoreCase(it) }
        if(existName!!.isEmpty()){
            throw NotFoundException(message = "The name $name does not exist", errorCode = "U-0002")
        }
        return existName.map { it.toUserResponse() }
    }

    override fun updateUser(uuid: String, userUpdateRequest: UserUpdateRequest) {
        getByUuid(uuid)
        val id: Long? = this.getById(uuid)
        val entity = userRepository.findById(id!!)
        userRepository.saveAndFlush(userUpdateRequest.toUserEntity(entity.get()))
    }

    override fun updatePartUser(uuid: String, userUpdateRequest: UserUpdateRequest) {
        getByUuid(uuid)
        val id: Long? = this.getById(uuid)
        val entity = userRepository.findById(id!!)
        userRepository.saveAndFlush(userUpdateRequest.toUserEntity(entity.get()))
    }

    override fun deleteUser(uuid: String) {
        getByUuid(uuid)
        val id = this.getById(uuid)
        val entity = userRepository.findById(id!!)
        userRepository.deleteById(entity.get().id!!)
    }

    private fun getByUuid(uuid: String) {
        val existsByUuid = userRepository.findByUuid(uuid)
        if (!existsByUuid.isPresent) {
            throw NotFoundException(message = "UUID [$uuid] does not exist", errorCode = "U-0003")
        }
    }

    private fun getById(uuid: String): Long?{
        val userEntity = userRepository.findByUuid(uuid)
        return userEntity.get().id
    }
}