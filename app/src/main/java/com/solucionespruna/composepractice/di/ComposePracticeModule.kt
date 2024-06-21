package com.solucionespruna.composepractice.di

import com.solucionespruna.composepractice.data.coffeshop.CoffeesRepository
import com.solucionespruna.composepractice.data.coffeshop.CoffeesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object ComposePracticeModule {

  @Provides
  fun provideDispatcher(): CoroutineDispatcher = Dispatchers.Main

  @Provides
  fun provideCoffeeRepository(): CoffeesRepository = CoffeesRepositoryImpl()
}