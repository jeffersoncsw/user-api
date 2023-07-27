package com.jwiltenburg.api.controllers

import com.jwiltenburg.api.controllers.request.UserRequest
import com.jwiltenburg.api.controllers.request.UserUpdateRequest
import com.jwiltenburg.api.controllers.response.UserResponse
import com.jwiltenburg.api.services.UserService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api")
class UserController(
        val userService: UserService
) {

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    fun createUser(@RequestBody userRequest: UserRequest) {
        userService.create(userRequest)
    }

    @GetMapping("/user")
    fun getAllUsers(@PageableDefault(page = 0, size = 10) page: Pageable): Page<UserResponse>{
        return userService.getAllUsers(page)
    }

    @GetMapping("/user/{name}")
    fun findByNameUser(@PathVariable("name") name: String): List<UserResponse>{
        return userService.findByNameUser(name)
    }

    @PutMapping("user/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateUser(@PathVariable uuid: String, @RequestBody userUpdateRequest: UserUpdateRequest){
        return userService.updateUser(uuid, userUpdateRequest)
    }

    @PatchMapping("user/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updatePartUser(@PathVariable uuid: String, @RequestBody userUpdateRequest: UserUpdateRequest){
        return userService.updatePartUser(uuid, userUpdateRequest)
    }

    @DeleteMapping("user/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteUser(@PathVariable uuid: String){
        return userService.deleteUser(uuid)
    }
}