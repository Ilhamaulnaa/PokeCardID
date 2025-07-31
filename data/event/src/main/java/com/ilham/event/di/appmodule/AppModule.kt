package com.ilham.event.di.appmodule

import com.ilham.event.data.remote.service.PokeApiService
import com.ilham.event.data.repository.PokemonRepository
import com.ilham.event.utils.Constatnt.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePokemonRepository(
        apiService: PokeApiService
    ) = PokemonRepository(apiService)

    @Provides
    @Singleton
    fun providePokemonApi(): PokeApiService{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(PokeApiService::class.java)

    }

}