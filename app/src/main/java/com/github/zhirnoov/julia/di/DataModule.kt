package com.github.zhirnoov.julia.di

import android.content.Context
import androidx.room.Room
import com.github.zhirnoov.julia.data.database.appdatabase.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule() {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "julia_db"
        ).build()

    @Provides
    fun provideCardDao(db : AppDatabase) = db.cardDao()

    @Provides
    fun provideCollectionDao(db : AppDatabase) = db.collectionDao()

    @Provides
    fun provideContext(@ApplicationContext context: Context) = context
}