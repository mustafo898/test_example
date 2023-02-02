package com.example.testexample.common

data class UIListState<T>(
    val error: String = "",
    val data: List<T>? = null,
    val isLoading: Boolean = false
)