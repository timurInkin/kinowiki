package com.example.kinowiki.presentation

import android.os.Bundle
import com.example.kinowiki.R
import com.example.kinowiki.presentation.common.BaseActivity
import com.example.kinowiki.presentation.topFilms.TopFilmsFragment

class MainActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        if (savedInstanceState == null) {
            val fragment = TopFilmsFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_activity_container, fragment)
                .commitAllowingStateLoss()
        }
    }
}