package com.example.testexample.presentation.main

import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.testexample.App
import com.example.testexample.databinding.FragmentMainBinding
import com.example.testexample.presentation.BaseFragment
import com.example.testexample.presentation.main.adapter.SimpleAdapter
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {
    @Inject
    lateinit var viewModel: MainViewModel

    private val adapter by lazy {
        SimpleAdapter(requireContext())
    }

    override fun onViewCreate() {
        App.appComponent.inject(this)

        binding.list.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            showMainProgress()
            viewModel.list.collectLatest { it ->
                it.data?.let { a ->
                    Log.d("sjsfhskjfhfhed", "onViewCreate: $a")
                    adapter.set(a)
                    hideMainProgress()
                }
                if (it.isLoading) {
                    showMainProgress()
                }
                if (it.error.isNotBlank()) {
                    hideMainProgress()
                }
            }
        }
    }
}