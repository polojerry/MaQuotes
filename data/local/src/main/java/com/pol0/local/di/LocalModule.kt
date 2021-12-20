package com.pol0.local.di

import android.content.Context
import androidx.room.Room
import com.pol0.local.database.QuotesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun providesQuotesDatabase(@ApplicationContext context: Context): QuotesDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            QuotesDatabase::class.java,
            DATABASE_NAME,
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    private const val DATABASE_NAME = "quotes_db"
}