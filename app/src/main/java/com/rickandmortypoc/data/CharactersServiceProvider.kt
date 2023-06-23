package com.rickandmortypoc.data

import com.rickandmortypoc.domain.CharacterRepository
import com.rickandmortypoc.data.repository.CharacterRepositoyImpl
import com.rickandmortypoc.data.common.RetrofitModule
import com.rickandmortypoc.data.remote.api.CharacterService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [RetrofitModule::class])
@InstallIn(SingletonComponent::class)
class CharactersServiceProvider {

    @Singleton
    @Provides
    fun provideCharacterApi(retrofit: Retrofit): CharacterService {
        return retrofit.create(CharacterService::class.java)
    }

    @Singleton
    @Provides
    fun provideCharacterRepository(apiService: CharacterService): CharacterRepository {
        return CharacterRepositoyImpl(apiService)
    }
}