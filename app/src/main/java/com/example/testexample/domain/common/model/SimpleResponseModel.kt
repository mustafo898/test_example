package com.example.testexample.domain.common.model

data class SimpleResponseModel(
    val attributes: List<AttributeModel>,
    val brand: String,
    val category: String,
    val id: Int,
    val image: ImageModel,
    val merchant: String,
    val name: String
)