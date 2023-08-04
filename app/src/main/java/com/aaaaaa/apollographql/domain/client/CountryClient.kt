package com.aaaaaa.apollographql.domain.client


interface CountryClient {
    suspend fun  getCountries(): List<CountryItem>
    suspend fun  getCountry(code:String): ExtraCountryInformationItem?
}