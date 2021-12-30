package com.example.kinowiki.presentation.filmDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kinowiki.domain.entity.Film
import com.example.kinowiki.presentation.common.SingleLiveEvent

class FilmDetailViewModel(private val film: Film) : ViewModel() {
    fun onBackPressed() {
        _backAction.value = Unit
    }

    private val  _filmState = MutableLiveData(film)
    val filmState : LiveData<Film> = _filmState
    private val  _backAction = SingleLiveEvent<Unit>()
    val backAction : LiveData<Unit> = _backAction


}