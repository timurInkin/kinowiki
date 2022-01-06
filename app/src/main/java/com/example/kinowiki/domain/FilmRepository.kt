package com.example.kinowiki.domain

import com.example.kinowiki.domain.entity.Film
import com.example.kinowiki.domain.entity.TopType


interface FilmRepository {
    suspend fun getTopFilms(topType: TopType, page:Int):List<Film>
}