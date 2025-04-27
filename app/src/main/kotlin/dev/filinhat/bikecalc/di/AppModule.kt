package dev.filinhat.bikecalc.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.filinhat.bikecalc.domain.repository.PressureCalcRepository
import dev.filinhat.bikecalc.data.repository.PressureCalcRepositoryImpl
import javax.inject.Singleton

/**
 * Модуль для внедрения зависимостей
 */
@Module
@InstallIn(SingletonComponent::class)
interface AppModule {
    @Singleton
    @Binds
    fun bindPressureCalcRepository(pressureCalcRepositoryImpl: PressureCalcRepositoryImpl): PressureCalcRepository
}
