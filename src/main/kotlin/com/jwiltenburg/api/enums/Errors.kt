package com.jwiltenburg.api.enums

enum class Errors(
        val code: String,
        val message: String
) {
    U0001(code = "U-0001", message = "There is no resource registered in the database"),
    U0002(code = "U-0002", message = "The name [ %s ] does not exist"),
    U0003(code = "U-0003", message = "UUID [ %s ] does not exist")
}