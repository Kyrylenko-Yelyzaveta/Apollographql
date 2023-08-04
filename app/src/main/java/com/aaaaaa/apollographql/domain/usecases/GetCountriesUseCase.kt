package com.aaaaaa.apollographql.domain.usecases

import com.aaaaaa.apollographql.domain.client.CountryClient
import com.aaaaaa.apollographql.domain.client.CountryItem
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject


@ViewModelScoped
class GetCountriesUseCase @Inject constructor(
    private val countryClient: CountryClient
) {
    suspend operator fun  invoke(): List<CountryItem>{
        return countryClient
            .getCountries()
            .sortedBy { it.name }
    }

}