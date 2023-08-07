package com.jwiltenburg.api.entities

import com.jwiltenburg.api.enums.Profile
import jakarta.persistence.*
import java.time.LocalDate
@Entity
@Table(name = "tb_user")
data class UserEntity (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @Column(name = "uuid_user")
        val uuid: String? = null,

        @Column(name = "name_user", nullable = false)
        val name: String,

        @Column(name = "email_user", nullable = false)
        val email: String,

        @Column(name = "date_of_birth_user", nullable = false)
        val dateOfBirth: LocalDate,

        @Column(name = "age_user", nullable = false)
        val age: Int,

        @Column(name = "password_user", nullable = false)
        val password: String,

        @Column(name = "role")
        @Enumerated(EnumType.STRING)
        @CollectionTable(name = "user_roles", joinColumns = [JoinColumn(name = "user_id")])
        @ElementCollection(targetClass = Profile::class, fetch = FetchType.EAGER)
        val roles: Set<Profile> = setOf()

)