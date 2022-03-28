package com.evren.rickandmorty.di

import com.evren.rickandmorty.data.repository.CharacterRepository
import com.evren.rickandmorty.data.repository.CharacterRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideCharacterRepository(characterRepository: CharacterRepositoryImpl): CharacterRepository
}