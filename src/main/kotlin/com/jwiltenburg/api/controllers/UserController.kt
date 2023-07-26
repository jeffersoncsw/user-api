package com.jwiltenburg.api.controllers

import com.jwiltenburg.api.controllers.request.UserRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api")
class UserController {

    @PostMapping("/user")
    fun createUser(@RequestBody userRequest: UserRequest) {
        println(userRequest)
    }

}