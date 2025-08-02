package com.ilham.event.di.appmodule

import android.content.Context
import androidx.room.Room
import com.ilham.event.data.local.AppDatabase
import com.ilham.event.data.local.dao.UserDao
import com.ilham.event.data.manger.UserSessionManager
import com.ilham.event.data.remote.service.PokeApiService
import com.ilham.event.data.repository.PokemonRepository
import com.ilham.event.data.repository.UserRepository
import com.ilham.event.utils.Constatnt.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "pokemon_db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideUserDao(db: AppDatabase): UserDao {
        return db.userDao()
    }

    @Singleton
    @Provides
    fun provideUserRepository(userDao: UserDao): UserRepository {
        return UserRepository(userDao)
    }

    @Singleton
    @Provides
    fun provideUserSessionManager(@ApplicationContext context: Context): UserSessionManager {
        return UserSessionManager(context)
    }

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