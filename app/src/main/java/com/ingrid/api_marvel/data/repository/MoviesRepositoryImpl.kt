package com.ingrid.api_marvel.data.repository

import com.ingrid.api_marvel.data.api.Retrofit.service
import com.ingrid.api_marvel.domain.model.ApiResult
import com.ingrid.api_marvel.domain.model.MovieResult
import com.ingrid.api_marvel.domain.repository.MoviesRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MoviesRepositoryImpl : MoviesRepository {

    override fun getMovies(resultCallback: (result: ApiResult) -> Unit) {
        service.returnMovies().enqueue(object : Callback<List<MovieResult>?> {

            override fun onResponse(
                    call: Call<List<MovieResult>?>,

                    response: Response<List<MovieResult>?>,
            ) {
                if (response.isSuccessful) {
                    resultCallback(ApiResult.Success(response.body()))
                }
            }

            override fun onFailure(call: Call<List<MovieResult>?>, t: Throwable) {

                resultCallback(ApiResult.ServerError(message = "Erro de conexão"))
            }

        })
    }
}