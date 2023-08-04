package com.aaaaaa.apollographql.data

import com.aaaaaa.apollographql.CountriesQuery
import com.aaaaaa.apollographql.CountryQuery
import com.aaaaaa.apollographql.domain.client.CountryClient
import com.aaaaaa.apollographql.domain.client.CountryItem
import com.aaaaaa.apollographql.domain.client.ExtraCountryInformationItem
import com.apollographql.apollo3.ApolloClient
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ApolloCountryClient @Inject constructor(
    private val apolloClient: ApolloClient
) : CountryClient {

    override suspend fun getCountries(): List<CountryItem> {
        return  apolloClient
            .query(CountriesQuery())
            .execute()
            .data
            ?.countries
            ?.map{it.toCountriesItem()}
            ?: emptyList()
    }

    override suspend fun getCountry(code: String): ExtraCountryInformationItem? {
        return apolloClient
            .query(CountryQuery(code))
            .execute()
            .data
            ?.country
            ?.toExtraCountryInformationItem()
    }
}