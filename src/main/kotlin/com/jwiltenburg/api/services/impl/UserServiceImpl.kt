package com.jwiltenburg.api.services.impl

import com.jwiltenburg.api.controllers.request.UserRequest
import com.jwiltenburg.api.controllers.request.UserUpdateRequest
import com.jwiltenburg.api.controllers.response.UserResponse
import com.jwiltenburg.api.entities.UserEntity
import com.jwiltenburg.api.extensions.toUserEntity
import com.jwiltenburg.api.extensions.toUserResponse
import com.jwiltenburg.api.repositories.UserRepository
import com.jwiltenburg.api.services.UserService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.Objects.isNull

@Service
class UserServiceImpl(
        val userRepository: UserRepository
) : UserService{
    override fun create(userRequest: UserRequest): UserEntity {
        return userRepository.save(userRequest.toUserEntity())
    }

    override fun getAllUsers(page: Pageable): Page<UserResponse> {
        val pages = userRepository.findAll(page)
        if(isNull(pages)){
            throw RuntimeException("Não existe nenhum recurso cadastrado na base de dados.")
        }
        return pages.map { it.toUserResponse() }
    }

    override fun findByNameUser(name: String?): List<UserResponse> {
        val existName = name?.let { userRepository.findByNameContainingIgnoreCase(it) }
        if(existName!!.isEmpty()){
            throw RuntimeException("Não existe nenhum recurso cadastrado na base de dados.")
        }
        return existName.map { it.toUserResponse() }
    }

    override fun updateUser(uuid: String, userUpdateRequest: UserUpdateRequest) {
        val existsByUuid = userRepository.findByUuid(uuid)
        if(!existsByUuid.isPresent){
            throw RuntimeException("Não existe nenhum recurso cadastrado na base de dados.")
        }
        val id: Long? = this.getById(uuid)
        val entity = userRepository.findById(id!!)
        userRepository.saveAndFlush(userUpdateRequest.toUserEntity(entity.get()))
    }

    override fun updatePartUser(uuid: String, userUpdateRequest: UserUpdateRequest) {
        val existsByUuid = userRepository.findByUuid(uuid)
        if(!existsByUuid.isPresent){
            throw RuntimeException("Não existe nenhum recurso cadastrado na base de dados.")
        }
        val id: Long? = this.getById(uuid)
        val entity = userRepository.findById(id!!)
        userRepository.saveAndFlush(userUpdateRequest.toUserEntity(entity.get()))
    }

    override fun deleteUser(uuid: String) {
        val id = this.getById(uuid)
        val entity = userRepository.findById(id!!)
        userRepository.deleteById(entity.get().id!!)
    }

    fun getById(uuid: String): Long?{
        val userEntity = userRepository.findByUuid(uuid)
        return userEntity.get().id
    }
}