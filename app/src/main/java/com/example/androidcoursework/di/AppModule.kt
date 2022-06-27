package com.example.androidcoursework.di

import com.example.androidcoursework.database.CharacterDao
import com.example.androidcoursework.database.EpisodeDao
import com.example.androidcoursework.database.LocationDao
import com.example.androidcoursework.database.QuoteDao
import com.example.androidcoursework.domain.api.ApiProvider
import com.example.androidcoursework.domain.api.ApiService
import com.example.androidcoursework.domain.api.RetrofitClient
import com.example.androidcoursework.domain.repository.*
import com.example.androidcoursework.domain.usecases.getCharacter.GetCharacterUseCase
import com.example.androidcoursework.domain.usecases.getCharacter.IGetCharacterUseCase
import com.example.androidcoursework.domain.usecases.getEpisode.GetEpisodeUseCase
import com.example.androidcoursework.domain.usecases.getEpisode.IGetEpisodeUseCase
import com.example.androidcoursework.domain.usecases.getLocation.GetLocationUseCase
import com.example.androidcoursework.domain.usecases.getLocation.IGetLocationUseCase
import com.example.androidcoursework.domain.usecases.getQuote.GetQuoteUseCase
import com.example.androidcoursework.domain.usecases.getQuote.IGetQuoteUseCase
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideRetrofitClient(): RetrofitClient =
        RetrofitClient()

    @Provides
    fun provideApiProvider(client: RetrofitClient): ApiProvider =
        ApiProvider(client)

    @Provides
    fun provideApi(apiProvider: ApiProvider): ApiService =
        apiProvider.getApi()

    @Provides
    fun provideViewModel(): MainViewModel =
        MainViewModel()

    @Provides
    fun provideCharacterUseCase(repo: ICharacterRepository): IGetCharacterUseCase =
        GetCharacterUseCase(repo)

    @Provides
    fun provideEpisodeUseCase(repo: IEpisodeRepository): IGetEpisodeUseCase =
        GetEpisodeUseCase(repo)

    @Provides
    fun provideLocationUseCase(repo: ILocationRepository): IGetLocationUseCase =
        GetLocationUseCase(repo)

    @Provides
    fun provideQuoteUseCase(repo: IQuoteRepository): IGetQuoteUseCase = GetQuoteUseCase(repo)

    @Provides
    fun provideCharacterRepo(
        apiProvider: ApiProvider,
        characterDao: CharacterDao
    ): ICharacterRepository = CharacterRepository(apiProvider, characterDao)

    @Provides
    fun provideEpisodeRepo(apiProvider: ApiProvider, episodeDao: EpisodeDao): IEpisodeRepository =
        EpisodeRepository(apiProvider, episodeDao)

    @Provides
    fun provideLocationRepository(apiProvider: ApiProvider, locationDao: LocationDao): ILocationRepository =
        LocationRepository(apiProvider, locationDao)

    @Provides
    fun provideQuoteRepository(apiProvider: ApiProvider, quoteDao: QuoteDao): IQuoteRepository =
        QuoteRepository(apiProvider, quoteDao)
}