package com.example.kinowiki.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)

class PreferencesModule {
    @Provides
    fun proideSharedPreferences(@ApplicationContext context: Context) = context.getSharedPreferences("main", Context.MODE_PRIVATE)
}