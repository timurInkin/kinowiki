package com.example.kinowiki.presentation.filmDetail

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kinowiki.R
import com.example.kinowiki.databinding.FilmDetailScreenBinding
import com.example.kinowiki.domain.entity.Film
import com.example.kinowiki.presentation.common.BaseFragment
import com.example.kinowiki.presentation.common.setImageUrl
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FilmDetailFragment : BaseFragment(R.layout.film_detail_screen) {

    companion object {
        fun newInstance(film: Film) = FilmDetailFragment().apply {
            arguments = bundleOf(FILM_DETAIL_DATA_KEY to film)

        }

        private const val FILM_DETAIL_DATA_KEY = "FILM_DETAIL_DATA_KEY"
        const val FILM_DETAIL_RESULT_KEY = "FILM_DETAIL_RESULT_KEY"
        const val FILM_DETAIL_RATING_KEY = "FILM_DETAIL_RATING_KEY"

    }
    @Inject
    lateinit var filmDetailViewModelFactory: FilmDetailViewModel.Factory

    private val viewBinding by viewBinding(FilmDetailScreenBinding::bind)
    private val viewModel by viewModels<FilmDetailViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                filmDetailViewModelFactory.create(arguments?.getParcelable(FILM_DETAIL_DATA_KEY)!!) as T
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.filmState.observe(viewLifecycleOwner) { film ->
            viewBinding.filmDetailYear.text = film.year.toString()
            viewBinding.filmDetailName.text = film.name
            viewBinding.filmDetailImg.setImageUrl(film.posterUrl)

        }
        viewModel.backAction.observe(viewLifecycleOwner) {
            closeScreen()
        }
        viewBinding.filmDetailBack.setOnClickListener{
            viewModel.onBackPressed()
        }
    }

    private fun closeScreen() {
        parentFragmentManager.popBackStack()
//        setFragmentResult(FILM_DETAIL_RESULT_KEY, bundleOf(FILM_DETAIL_RATING_KEY to 5))
    }
}
