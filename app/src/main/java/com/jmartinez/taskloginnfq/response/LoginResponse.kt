package com.jmartinez.taskloginnfq.response

data class LoginResponse(
    val refreshToken: String,
    val token: String
)