package com.aaaaaa.apollographql.data.di

import com.aaaaaa.apollographql.data.ApolloCountryClient
import com.aaaaaa.apollographql.domain.client.CountryClient
import com.apollographql.apollo3.ApolloClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


const val serverUrl = "https://countries.trevorblades.com/graphql"

@InstallIn(SingletonComponent::class)
@Module
class DataModule {
    @Provides
    @Singleton
    fun provideApolloClient(): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl(serverUrl)
            .build()
    }

    @Provides
    @Singleton
    fun provideCountryClient(apolloClient: ApolloClient): CountryClient {
        return ApolloCountryClient(apolloClient)
    }
}
