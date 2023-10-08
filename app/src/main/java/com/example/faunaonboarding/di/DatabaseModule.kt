package com.example.faunaonboarding.di

import com.example.faunaonboarding.animal.AnimalDataSource
import com.example.faunaonboarding.animal.AnimalRepository
import com.example.faunaonboarding.createAccount.AccountDataSource
import com.example.faunaonboarding.login.LoginRepository
import com.example.faunaonboarding.util.User
import com.example.faunaonboarding.welcome.WelcomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    fun provideProcessContext() : CoroutineContext {
        return Dispatchers.Default
    }

    @Provides
    fun provideAnimalRepository(animalDataSource: AnimalDataSource) = AnimalRepository(animalDataSource)

    @Singleton
    @Provides
    fun providesLoginRepository(accountDataSource: AccountDataSource) = LoginRepository(accountDataSource)

    @Provides
    fun providesWelcomeRepository(user: User) = WelcomeRepository(user)
}