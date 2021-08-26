package com.larissa.integrativeproject.domain

import com.larissa.integrativeproject.data.model.Movies

interface CheckListener {
    fun onFavoriteCheckListener(movie: Movies, isChecked: Boolean, position: Int)
    fun onGenreCheckListener(genreIds: MutableList<Int>)
}

