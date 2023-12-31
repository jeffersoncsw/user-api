package com.jwiltenburg.api.repositories

import com.jwiltenburg.api.entities.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface UserRepository: JpaRepository<UserEntity, Long> {
    fun findByNameContainingIgnoreCase(name: String): List<UserEntity>
    fun findByUuid(uuid: String): Optional<UserEntity>
    fun existsByEmail(email: String): Boolean
}