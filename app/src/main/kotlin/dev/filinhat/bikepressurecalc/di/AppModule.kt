package dev.filinhat.bikepressurecalc.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.filinhat.bikepressurecalc.data.repository.PressureCalcRepository
import dev.filinhat.bikepressurecalc.data.repository.PressureCalcRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {

    @Singleton
    @Binds
    fun bindPressureCalcRepository(
        pressureCalcRepositoryImpl: PressureCalcRepositoryImpl
    ): PressureCalcRepository
}