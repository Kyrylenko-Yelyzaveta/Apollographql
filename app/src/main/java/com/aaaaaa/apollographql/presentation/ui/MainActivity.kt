package com.aaaaaa.apollographql.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aaaaaa.apollographql.databinding.ActivityMainBinding
import com.aaaaaa.apollographql.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity(
) : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CountriesAdapter

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        binding.recView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        initObservers()

        setContentView(binding.root)
    }

    private fun initObservers() {
        viewModel.state.observe(this) {

            adapter = CountriesAdapter(it) { countryItem, onExtraDataReady ->
                lifecycleScope.launch {
                    val extraData = viewModel.selectCountry(countryItem.code)
                    if (extraData != null) {
                        onExtraDataReady(extraData)
                    }
                }
            }
            binding.recView.adapter = adapter

//            Log.d("Adapter data", it.toString())
        }
    }

}


