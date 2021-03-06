package com.example.kinowiki.data.network

import android.content.SharedPreferences
import com.example.kinowiki.data.network.entity.TopFilmsResponse
import com.example.kinowiki.domain.FilmRepository
import com.example.kinowiki.domain.entity.Film
import com.example.kinowiki.domain.entity.TopType
import javax.inject.Inject

class FilmRepositoryImpl @Inject constructor(
    private val filmApi: FilmApi,
    private val sharedPreferences: SharedPreferences
) : FilmRepository {
    override suspend fun getTopFilms(topType: TopType, page: Int): List<Film> {
        return filmApi.getTopFilms(type = topType.serverType, page)
            .toFilms()
    }


    suspend fun search(yearFrom: Int?, yearTo: Int?): List<Film> {

        return filmApi.search(yearFrom, yearTo)
            .toFilms()
    }

    private fun TopFilmsResponse.toFilms(): List<Film> =
        films?.mapNotNull{ filmNw ->
        Film(
            name = filmNw.nameRu ?: return@mapNotNull null,
            year = filmNw.year?.toIntOrNull() ?: return@mapNotNull null,
            posterUrl = filmNw.posterUrl ?: "",
            posterUrlPreview = filmNw.posterUrlPreview ?: "",
            rating = filmNw.rating
        )
    } ?: emptyList()
}
