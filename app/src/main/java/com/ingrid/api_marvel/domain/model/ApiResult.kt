package com.ingrid.api_marvel.domain.model

sealed class ApiResult {
    class Success(val movies: List<MovieResult>?) : ApiResult()
    class ServerError(val message: String? = null) : ApiResult()
}