package com.example.testexample.data.remote.dto

data class APIErrorResponse(
    val message: String,
    val statusCode: Int,
    val success: Boolean
)
