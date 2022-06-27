package com.example.androidcoursework.di

import android.app.Application
import android.content.Context
import com.example.androidcoursework.database.*
import dagger.Module
import dagger.Provides

@Module
class RoomModule(private val application: Application) {
    @Provides
    fun getBalanceDao(appDatabase: AppDatabase): CharacterDao = appDatabase.characterDao()

    @Provides
    fun getUserDao(appDatabase: AppDatabase): EpisodeDao = appDatabase.episodeDao()

    @Provides
    fun getTariffDao(appDatabase: AppDatabase): LocationDao = appDatabase.locationDao()

    @Provides
    fun getDbEntityDao(appDatabase: AppDatabase): QuoteDao = appDatabase.quoteDao()

    @Provides
    fun provideRoomDb(): AppDatabase = AppDatabase.getDatabase(provideAppContext())

    @Provides
    fun provideAppContext(): Context = application.applicationContext
}