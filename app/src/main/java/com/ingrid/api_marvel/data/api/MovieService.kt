package com.ingrid.api_marvel.data.api

import com.ingrid.api_marvel.domain.model.MovieResult
import retrofit2.Call
import retrofit2.http.GET

interface MovieService {

    @GET("saga")
    fun returnMovies(): Call<List<MovieResult>>
}
