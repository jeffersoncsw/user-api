package com.jwiltenburg.api.enums

enum class Errors(
        val code: String,
        val message: String
) {
    U0001(code = "U-0001", message = "Invalid Fields Request"),
    U1001(code = "U-1001", message = "There is no resource registered in the database"),
    U1002(code = "U-1002", message = "The name [ %s ] does not exist"),
    U1003(code = "U-1003", message = "UUID [ %s ] does not exist")
}