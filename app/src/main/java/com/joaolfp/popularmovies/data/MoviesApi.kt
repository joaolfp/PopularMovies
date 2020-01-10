package com.joaolfp.popularmovies.data

import com.joaolfp.popularmovies.data.model.ResultDTO
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {
    @GET("movie/popular")
    fun getMovies(
        @Query("api_key") apiKey: String
    ): Observable<ResultDTO>
}