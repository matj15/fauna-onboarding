package com.example.faunaonboarding.di

import com.apollographql.apollo.ApolloClient
import com.example.faunaonboarding.MainViewModel
import com.example.faunaonboarding.animal.AnimalDataSource
import com.example.faunaonboarding.login.LoginRepository
import com.example.faunaonboarding.login.PhoneNumberViewModel
import com.example.faunaonboarding.welcome.WelcomeRepository
import com.example.faunaonboarding.welcome.WelcomeViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ViewModelModule {

    @Provides
    fun providesMainViewModel(): MainViewModel = MainViewModel()

    @Singleton
    @Provides
    fun provideWelcomeViewModel(welcomeRepository: WelcomeRepository): WelcomeViewModel =
        WelcomeViewModel(welcomeRepository)

    @Singleton
    @Provides
    fun providePhoneNumberViewModel(loginRepository: LoginRepository): PhoneNumberViewModel =
        PhoneNumberViewModel(loginRepository)

    @Provides
    fun providesAnimalDataSource() = AnimalDataSource()

}