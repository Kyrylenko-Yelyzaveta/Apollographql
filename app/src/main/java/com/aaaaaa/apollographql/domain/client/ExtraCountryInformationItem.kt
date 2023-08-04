package com.aaaaaa.apollographql.domain.client


data class ExtraCountryInformationItem( val name: String,
                                        val capital: String?,
                                        val emoji: String,
                                        val currency: String?,
                                        val languages: List<String>,
                                        val continent: String,
)