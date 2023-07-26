package com.jwiltenburg.api.entities

import jakarta.persistence.*
import java.time.LocalDate
@Entity
@Table(name = "tb_user")
data class UserEntity (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,
        val name: String,
        val email: String,
        val dateOfBirth: LocalDate,
        val age: Integer,
)