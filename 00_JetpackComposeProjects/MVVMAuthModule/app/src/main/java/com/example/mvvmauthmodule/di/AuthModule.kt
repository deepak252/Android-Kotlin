package com.example.mvvmauthmodule.di

import com.example.mvvmauthmodule.data.AuthRepositoryImpl
import com.example.mvvmauthmodule.domain.repository.AuthRepository
import com.example.mvvmauthmodule.domain.use_case.ValidateLoginInputUseCase
import com.example.mvvmauthmodule.domain.use_case.ValidateRegisterInputUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideValidateLoginInputUseCase() : ValidateLoginInputUseCase{
        return ValidateLoginInputUseCase()
    }

    @Provides
    @Singleton
    fun provideValidateRegisterInputUseCase() : ValidateRegisterInputUseCase{
        return ValidateRegisterInputUseCase()
    }

    @Provides
    @Singleton
    fun provideAuthRepository() : AuthRepository{
        return AuthRepositoryImpl()
    }
}