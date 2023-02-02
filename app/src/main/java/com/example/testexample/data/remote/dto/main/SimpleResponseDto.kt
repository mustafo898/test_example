package com.example.testexample.data.remote.dto.main

data class SimpleResponseDto(
    val attributes: List<AttributeDto>,
    val brand: String,
    val category: String,
    val id: Int,
    val image: ImageDto,
    val merchant: String,
    val name: String
)