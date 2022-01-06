package com.example.kinowiki.presentation.common

import android.util.Log
import com.example.kinowiki.domain.entity.Film
import com.example.kinowiki.domain.entity.TopType
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun CoroutineScope.launchWithErrorHandler(
    onError : (Throwable) -> Unit = {},
    block: suspend () -> Unit
)
     {
    launch (CoroutineExceptionHandler { _, throwable ->
            Log.e("launchWithErrorHandler", throwable.message, throwable)
        onError(throwable)
        }){
            block()
        }

    }