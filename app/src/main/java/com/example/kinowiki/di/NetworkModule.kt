package com.example.kinowiki.di

import android.content.SharedPreferences
import com.example.kinowiki.data.network.FilmApi
import com.example.kinowiki.data.network.FilmApi.Companion.X_API_KEY
import com.example.kinowiki.data.network.FilmRepositoryImpl
import com.example.kinowiki.domain.FilmRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.migration.DisableInstallInCheck
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    companion object {


        @Provides
        fun provideFilmApi(): FilmApi = Retrofit.Builder()
            .baseUrl("https://kinopoiskapiunofficial.tech")
            .client(
                OkHttpClient.Builder()
                    .addInterceptor { chain: Interceptor.Chain ->
                        val request = chain.request()
                            .newBuilder()
                            .header("X-API-KEY", X_API_KEY)
                            .build()

                        chain.proceed(request)
                    }
                    .addInterceptor (HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build()
            )
            .addConverterFactory(
                Json(builderAction = {
                    isLenient = true
                    ignoreUnknownKeys = true
                }).asConverterFactory("application/json".toMediaType())
            )
            .build()
            .create()
    }

    @Binds
    abstract fun getRepository(filmRepositoryImpl: FilmRepositoryImpl): FilmRepository
}
