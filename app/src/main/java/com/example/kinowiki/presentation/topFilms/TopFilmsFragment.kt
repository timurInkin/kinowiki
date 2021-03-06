package com.example.kinowiki.presentation.topFilms

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.*
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kinowiki.R
import com.example.kinowiki.databinding.TopFilmsScreenBinding
import com.example.kinowiki.domain.entity.Film
import com.example.kinowiki.presentation.common.BaseFragment
import com.example.kinowiki.presentation.common.navigate
import com.example.kinowiki.presentation.filmDetail.FilmDetailFragment
import com.example.kinowiki.presentation.filmDetail.FilmDetailFragment.Companion.FILM_DETAIL_RATING_KEY
import com.example.kinowiki.presentation.filmDetail.FilmDetailFragment.Companion.FILM_DETAIL_RESULT_KEY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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

        val topFilmsAdapter = TopFilmsAdapter(viewModel::onFilmClicked)
        with(viewBinding.topFilmList) {
            adapter = topFilmsAdapter
            layoutManager = LinearLayoutManager(context)
        }
        viewModel.screenState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is TopFilmsState.Error -> {
                    viewBinding.topFilmError.isVisible = true
                    viewBinding.topFilmProgress.isVisible = false
                    viewBinding.topFilmList.isVisible = false
                }
                is TopFilmsState.Loading -> {
                    viewBinding.topFilmError.isVisible = false
                    viewBinding.topFilmProgress.isVisible = true
                    viewBinding.topFilmList.isVisible = false
                }
                is TopFilmsState.Success -> {
                    viewBinding.topFilmError.isVisible = false
                    viewBinding.topFilmProgress.isVisible = false
                    viewBinding.topFilmList.isVisible = true
                    topFilmsAdapter.submitList(state.films)

                }
            }
        }
        viewModel.openDetailAction.observe(viewLifecycleOwner) {
            openDetail(it)
        }
    }

    private fun openDetail(film: Film) {
        parentFragmentManager.navigate(FilmDetailFragment.newInstance(film))
    }

    private fun  openSearch() {
        parentFragmentManager.navigate(SearchFragment())
    }


}
class SearchFragment : BaseFragment(0) {

}


