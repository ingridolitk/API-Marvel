package com.ingrid.api_marvel.domain.repository

import com.ingrid.api_marvel.domain.model.ApiResult

interface MoviesRepository {
    fun getMovies(usersResultCallback: (result: ApiResult) -> Unit)
}