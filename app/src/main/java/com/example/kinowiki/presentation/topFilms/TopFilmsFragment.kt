package com.example.kinowiki.presentation.topFilms

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.*
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kinowiki.R
import com.example.kinowiki.databinding.TopFilmsScreenBinding
import com.example.kinowiki.domain.entity.Film
import com.example.kinowiki.presentation.common.BaseFragment
import com.example.kinowiki.presentation.filmDetail.FilmDetailFragment
import com.example.kinowiki.presentation.filmDetail.FilmDetailFragment.Companion.FILM_DETAIL_RATING_KEY
import com.example.kinowiki.presentation.filmDetail.FilmDetailFragment.Companion.FILM_DETAIL_RESULT_KEY

class TopFilmsFragment : BaseFragment(R.layout.top_films_screen) {
    private val viewBinding by viewBinding(TopFilmsScreenBinding::bind)
    private val viewModel by viewModels<TopFilmsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener(FILM_DETAIL_RESULT_KEY) {requestKey, bundle ->
           if (requestKey == FILM_DETAIL_RESULT_KEY ) {
              val rating = bundle.getInt(FILM_DETAIL_RATING_KEY)
               showRating(rating)
           }
        }
    }
    private fun showRating(rating: Int) {
        Toast.makeText(requireContext(),"$rating", Toast.LENGTH_SHORT).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.countState.observe(viewLifecycleOwner) { count ->
            viewBinding.topFilmCount.text = count.toString()
        }
        viewBinding.topFilmAdd.setOnClickListener() {
            viewModel.onAdd()
        }
        viewBinding.topFilmShowDetail.setOnClickListener{
            openDetail(Film("Lord of Campton", 2022))
        }
        }
        private fun openDetail(film: Film) {
            parentFragmentManager.commit(allowStateLoss = true) {
                /**add - накладывать фраменты поверх.
                 * можно делать два фрагмента на одном экране
                  */
                replace(
                    R.id.main_activity_container,
                    FilmDetailFragment.newInstance(film))
                addToBackStack(null)
            }
        }
    }


