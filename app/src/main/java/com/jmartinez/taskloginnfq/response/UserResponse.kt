package com.jmartinez.taskloginnfq.response

data class UserResponse(
    val address: String,
    val firstName: String,
    val image: String,
    val lastName: String,
    val phone: String,
    val uuid: String
)