package com.example.kinowiki.presentation.common

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.example.kinowiki.R
import com.example.kinowiki.presentation.topFilms.SearchFragment

 fun FragmentManager.navigate(fragment: Fragment) {
    commit(allowStateLoss = true) {
        /**add - накладывать фраменты поверх.
         * можно делать два фрагмента на одном экране
         */
        /**add - накладывать фраменты поверх.
         * можно делать два фрагмента на одном экране
         */
        setCustomAnimations(
            R.anim.slide_from_right, R.anim.slide_to_left,
            R.anim.slide_from_left, R.anim.slide_to_right)
        replace(R.id.main_activity_container, fragment)
        addToBackStack(null)
    }
}