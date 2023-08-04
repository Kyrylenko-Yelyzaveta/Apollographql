package com.aaaaaa.apollographql.domain.usecases

import com.aaaaaa.apollographql.domain.client.CountryClient
import com.aaaaaa.apollographql.domain.client.ExtraCountryInformationItem
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject


@ViewModelScoped
class GetExtraCountryInformationItemUseCase @Inject constructor(
    private val countryClient: CountryClient,

    ) {
    suspend operator fun invoke(code: String): ExtraCountryInformationItem? {
        return countryClient.getCountry(code = code)
    }

}