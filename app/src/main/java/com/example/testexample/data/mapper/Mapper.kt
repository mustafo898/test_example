package com.example.testexample.data.mapper

import com.example.testexample.data.remote.dto.main.AttributeDto
import com.example.testexample.data.remote.dto.main.ImageDto
import com.example.testexample.data.remote.dto.main.SimpleResponseDto
import com.example.testexample.domain.common.model.AttributeModel
import com.example.testexample.domain.common.model.ImageModel
import com.example.testexample.domain.common.model.SimpleResponseModel

fun SimpleResponseDto.toModel(): SimpleResponseModel {
    return SimpleResponseModel(
        attributes.map { it.toModel() },
        brand,
        category,
        id,
        image.toModel(),
        merchant,
        name
    )
}

fun AttributeDto.toModel(): AttributeModel {
    return AttributeModel(name, value)
}

fun ImageDto.toModel(): ImageModel {
    return ImageModel(height, url, width)
}