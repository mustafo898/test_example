package com.example.testexample.presentation.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testexample.common.UIListState
import com.example.testexample.domain.common.Resource
import com.example.testexample.domain.common.model.SimpleResponseModel
import com.example.testexample.domain.use_case.MainUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val useCase: MainUseCase
) : ViewModel() {

    private val _list = MutableStateFlow(UIListState<SimpleResponseModel>())
    val list = _list.asStateFlow()

    init {
        viewModelScope.launch {
            getList()
        }
    }

    private suspend fun getList() {
        useCase().onEach { it ->
            when (it) {
                is Resource.Error -> {
                    _list.value = UIListState(error = it.message ?: "")
                }
                is Resource.Loading -> {
                    _list.value = UIListState(isLoading = true)
                }
                is Resource.Success -> {
                    Log.d("mmmmmm", "teach->${it.data}")
                    _list.value = UIListState(data = it.data)
                }
            }
        }.launchIn(CoroutineScope(Dispatchers.IO))
    }

}