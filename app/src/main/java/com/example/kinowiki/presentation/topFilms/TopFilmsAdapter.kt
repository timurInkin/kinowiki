package com.example.kinowiki.presentation.topFilms

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kinowiki.databinding.ItemFilmBinding
import com.example.kinowiki.presentation.topFilms.TopFilmsAdapter.ViewHolder
import com.example.kinowiki.domain.entity.Film
import com.example.kinowiki.presentation.common.setImageUrl

class TopFilmsAdapter(
    private val onFilmClicked:(Film) -> Unit
): ListAdapter<Film, ViewHolder>(
    object : DiffUtil.ItemCallback<Film>() {
        override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean = oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean = oldItem == newItem

    }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemFilmBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding) {
            val item = getItem(position)
            itemFilmName.text = item.name
            itemFilmYear.text = item.year.toString()
            itemFilmRating.text = "Wait rating: ${item.rating}"
            itemFilmImg.setImageUrl(item.posterUrlPreview)
            root.setOnClickListener{onFilmClicked(item)}
        }
    }
    class ViewHolder (val binding : ItemFilmBinding) : RecyclerView.ViewHolder(binding.root)

}