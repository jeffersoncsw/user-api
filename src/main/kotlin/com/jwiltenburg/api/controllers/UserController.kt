package com.jwiltenburg.api.controllers

import com.jwiltenburg.api.controllers.request.UserRequest
import com.jwiltenburg.api.services.UserService
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

}