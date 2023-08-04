package com.aaaaaa.apollographql.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aaaaaa.apollographql.domain.client.CountryItem
import com.aaaaaa.apollographql.domain.client.ExtraCountryInformationItem
import com.aaaaaa.apollographql.domain.usecases.GetCountriesUseCase
import com.aaaaaa.apollographql.domain.usecases.GetExtraCountryInformationItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCountriesUseCase: GetCountriesUseCase,
    private val getExtraCountryInformationItemUseCase: GetExtraCountryInformationItemUseCase
) : ViewModel() {


    private val _state: MutableLiveData<List<CountryItem>?> = MutableLiveData()
    val state: LiveData<List<CountryItem>?> = _state

    init {
        viewModelScope.launch {
            _state.value = getCountriesUseCase()
            Log.d("Adapter Countries", _state.value.toString())

        }
    }

    suspend fun selectCountry(code: String): ExtraCountryInformationItem? {
       val extraData = viewModelScope.async {
            getExtraCountryInformationItemUseCase(code)
        }
        return extraData.await()
    }
}

