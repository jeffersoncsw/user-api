package com.jwiltenburg.api.services.impl

import com.jwiltenburg.api.controllers.request.UserRequest
import com.jwiltenburg.api.controllers.request.UserUpdatePartRequest
import com.jwiltenburg.api.controllers.request.UserUpdateRequest
import com.jwiltenburg.api.controllers.response.UserResponse
import com.jwiltenburg.api.entities.UserEntity
import com.jwiltenburg.api.exceptions.NotFoundException
import com.jwiltenburg.api.extensions.toUserEntity
import com.jwiltenburg.api.extensions.toUserResponse
import com.jwiltenburg.api.repositories.UserRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.LocalDate
import java.util.*

@ExtendWith(SpringExtension::class)
class UserServiceImplTest {

    @Mock
    private lateinit var userRepository: UserRepository

    @InjectMocks
    private lateinit var userServiceImpl: UserServiceImpl

    private lateinit var userRequest: UserRequest
    private lateinit var userUpdateRequest: UserUpdateRequest
    private lateinit var userUpdatePartRequest: UserUpdatePartRequest
    private lateinit var userResponse: UserResponse
    private lateinit var userEntity: UserEntity

    @BeforeEach
    fun setup(){
        userRequest = createRequest()
        userUpdateRequest = createUserUpdateRequest()
        userUpdatePartRequest = createUserUpdatePartRequest()
        userResponse = createUserResponse()
        userEntity = createUserEntity()
    }

    @Test
    fun `should create user`(){
        val fakeUser = userRequest

        `when`(userRepository.save(fakeUser.toUserEntity())).thenReturn(userEntity)

        userServiceImpl.create(fakeUser)

        verify(userRepository, times(1)).save(fakeUser.toUserEntity())

    }

    @Test
    fun `should return users when name is informed`(){
        val name = "jefferson"

        val fakeUsers = listOf(userRequest.toUserEntity(), userRequest.toUserEntity())

        `when`(userRepository.findByNameContainingIgnoreCase(name)).thenReturn(fakeUsers)

        val nameUsers = userServiceImpl.findByNameUser(name)

        assertEquals(fakeUsers.map { it.toUserResponse() }, nameUsers)

        verify(userRepository, times(1)).findByNameContainingIgnoreCase(name)

    }

    @Test
    fun `should return users when name is partial informed`(){
        val name = "j"

        val fakeUsers = listOf(userRequest.toUserEntity(), userRequest.toUserEntity())

        `when`(userRepository.findByNameContainingIgnoreCase(name)).thenReturn(fakeUsers)

        val nameUsers = userServiceImpl.findByNameUser(name)

        assertEquals(fakeUsers.map { it.toUserResponse() }, nameUsers)

        verify(userRepository, times(1)).findByNameContainingIgnoreCase(name)

    }

    @Test
    fun `should throw error when user not found by name`(){
        val name = "Jefferson"

        `when`(userRepository.findByNameContainingIgnoreCase(name)).thenReturn(emptyList())

        val error = assertThrows<NotFoundException>{ userServiceImpl.findByNameUser(name)}

        assertEquals("The name [ $name ] does not exist", error.message)
        assertEquals("U-1002", error.errorCode)

        verify(userRepository, times(1)).findByNameContainingIgnoreCase(name)

    }

    @Test
    fun `should update user`(){
        val uuid = UUID.randomUUID().toString()
        val fakeUser = userEntity.copy(uuid = uuid)

        `when`(userRepository.findByUuid(uuid)).thenReturn(Optional.of(fakeUser))
        `when`(userRepository.findById(userEntity.id!!)).thenReturn(Optional.of(fakeUser))
        `when`(userRepository.saveAndFlush(userUpdateRequest.toUserEntity(fakeUser))).thenReturn(fakeUser)

        userServiceImpl.updateUser(uuid, userUpdateRequest)

        verify(userRepository, times(2)).findByUuid(uuid)
        verify(userRepository, times(1)).findById(userEntity.id!!)
        verify(userRepository, times(1)).saveAndFlush(userUpdateRequest.toUserEntity(fakeUser))

    }

    @Test
    fun `should update partial user`(){
        val uuid = UUID.randomUUID().toString()
        val fakeUser = userEntity.copy(uuid = uuid)

        `when`(userRepository.findByUuid(uuid)).thenReturn(Optional.of(fakeUser))
        `when`(userRepository.findById(userEntity.id!!)).thenReturn(Optional.of(fakeUser))
        `when`(userRepository.saveAndFlush(userUpdatePartRequest.toUserEntity(fakeUser))).thenReturn(fakeUser)

        userServiceImpl.updatePartUser(uuid, userUpdatePartRequest)

        verify(userRepository, times(2)).findByUuid(uuid)
        verify(userRepository, times(1)).findById(userEntity.id!!)
        verify(userRepository, times(1)).saveAndFlush(userUpdatePartRequest.toUserEntity(fakeUser))

    }

    @Test
    fun `should delete user`(){
        val uuid = UUID.randomUUID().toString()
        val fakeUser = userEntity.copy(uuid = uuid)

        `when`(userRepository.findByUuid(uuid)).thenReturn(Optional.of(fakeUser))
        `when`(userRepository.findById(userEntity.id!!)).thenReturn(Optional.of(fakeUser))
        doNothing().`when`(userRepository).deleteById(userEntity.id!!)

        userServiceImpl.deleteUser(uuid)

        verify(userRepository, times(2)).findByUuid(uuid)
        verify(userRepository, times(1)).findById(userEntity.id!!)

    }

    @Test
    fun `should return true when email available`(){
        val email = "${UUID.randomUUID().toString()}@email.com"

        `when`(userRepository.existsByEmail(email)).thenReturn(false)

        val emailAvailable = userServiceImpl.emailAvailable(email)

        assertTrue(emailAvailable)

        verify(userRepository, times(1)).existsByEmail(email)
    }

    @Test
    fun `should return false when email unavailable`(){
        val email = "${UUID.randomUUID().toString()}@email.com"

        `when`(userRepository.existsByEmail(email)).thenReturn(true)

        val emailAvailable = userServiceImpl.emailAvailable(email)

        assertFalse(emailAvailable)

        verify(userRepository, times(1)).existsByEmail(email)
    }

    @Test
    fun `should get for uuid`(){
        val uuid = UUID.randomUUID().toString()
        val fakeUser = userEntity.copy(uuid = uuid)

        `when`(userRepository.findByUuid(uuid)).thenReturn(Optional.of(fakeUser))

        userServiceImpl.getByUuid(uuid)

        verify(userRepository, times(1)).findByUuid(uuid)
    }

    @Test
    fun `should get for uuid return not found`(){
        val uuid = UUID.randomUUID().toString()

        `when`(userRepository.findByUuid(uuid)).thenReturn(Optional.empty())

        val error = assertThrows<NotFoundException>{ userServiceImpl.getByUuid(uuid)}

        assertEquals("UUID [ $uuid ] does not exist", error.message)
        assertEquals("U-1003", error.errorCode)

        verify(userRepository, times(1)).findByUuid(uuid)

    }

    private fun createRequest(): UserRequest {
        return UserRequest(
                name = "Jefferson",
                uuid = UUID.randomUUID().toString(),
                email = "${UUID.randomUUID()}@email.com",
                dateOfBirth = LocalDate.now().minusYears(10),
                age = 10
        )
    }

    private fun createUserUpdateRequest(): UserUpdateRequest {
        return UserUpdateRequest(
                name = "Jefferson Cesar da Silva",
                email = "${UUID.randomUUID()}@email.com",
                dateOfBirth = LocalDate.now().minusYears(12),
                age = 12
        )
    }

    private fun createUserUpdatePartRequest(): UserUpdatePartRequest {
        return UserUpdatePartRequest(
                name = "Jefferson Cesar",
                email = "${UUID.randomUUID()}@email.com",
                dateOfBirth = LocalDate.now().minusYears(11),
                age = 11
        )
    }

    private fun createUserResponse(): UserResponse {
        return UserResponse(
                name = "Jefferson",
                uuid = UUID.randomUUID().toString(),
                email = "${UUID.randomUUID()}@email.com",
                dateOfBirth = LocalDate.now().minusYears(10),
                age = 10
        )
    }

    private fun createUserEntity(): UserEntity {
        return UserEntity(
                id = 1,
                name = "Jefferson",
                uuid = UUID.randomUUID().toString(),
                email = "${UUID.randomUUID()}@email.com",
                dateOfBirth = LocalDate.now().minusYears(10),
                age = 10
        )
    }
}