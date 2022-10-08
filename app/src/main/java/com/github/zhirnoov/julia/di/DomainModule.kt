package com.github.zhirnoov.julia.di

import com.github.zhirnoov.julia.data.data_sources.card.CardLocalDataSource
import com.github.zhirnoov.julia.data.data_sources.collection.CollectionLocalDataSource
import com.github.zhirnoov.julia.data.repository.CardRepositoryImpl
import com.github.zhirnoov.julia.data.repository.CollectionRepositoryImpl
import com.github.zhirnoov.julia.domain.repository.CardRepository
import com.github.zhirnoov.julia.domain.repository.CollectionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    fun provideCardRepository(cardLocalDataSource: CardLocalDataSource): CardRepository {
        return CardRepositoryImpl(cardLocalDataSource = cardLocalDataSource)
    }

    @Provides
    fun provideCollectionRepository(collectionLocalDataSource: CollectionLocalDataSource): CollectionRepository {
        return CollectionRepositoryImpl(collectionLocalDataSource = collectionLocalDataSource)
    }
}