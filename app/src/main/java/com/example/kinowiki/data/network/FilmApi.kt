package com.example.kinowiki.data.network

import com.example.kinowiki.data.network.entity.TopFilmsResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface FilmApi {
    @GET("/api/v2.2/films/top")
    suspend fun getTopFilms(
        @Query("type") type: String,
        @Query("page") page: Int,
    ):TopFilmsResponse

    @GET("/api/v2.1/films/search-by-filters")
    suspend fun search(
        @Query("yearFrom") yearFrom: Int?,
        @Query("yearTo") yearTo: Int?
    ):TopFilmsResponse

    companion object{
        const val X_API_KEY = "9ecb80c1-1879-4d80-a28f-353a78998c4b"
    }
}