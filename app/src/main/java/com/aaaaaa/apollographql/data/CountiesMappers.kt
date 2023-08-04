package com.aaaaaa.apollographql.data

import com.aaaaaa.apollographql.CountriesQuery
import com.aaaaaa.apollographql.CountryQuery
import com.aaaaaa.apollographql.domain.client.CountryItem
import com.aaaaaa.apollographql.domain.client.ExtraCountryInformationItem

fun CountriesQuery.Country.toCountriesItem(): CountryItem {
    return CountryItem(
        code = code,
        name = name,
        capital = capital ?: "No capital",
        emoji = emoji,
        currency = currency ?: "No native currency",
    )
}


fun CountryQuery.Country.toExtraCountryInformationItem(): ExtraCountryInformationItem {
    return ExtraCountryInformationItem(
        name = name,
        capital = capital ?: "No capital",
        emoji = emoji,
        currency = currency ?: "No native currency",
        languages = languages.mapNotNull { it.name },
        continent = continent.name,
    )
}