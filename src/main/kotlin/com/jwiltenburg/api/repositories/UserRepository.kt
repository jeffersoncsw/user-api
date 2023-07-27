package com.jwiltenburg.api.repositories

import com.jwiltenburg.api.entities.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<UserEntity, Long> {
    fun findByNameContaining(name: String): List<UserEntity>
}