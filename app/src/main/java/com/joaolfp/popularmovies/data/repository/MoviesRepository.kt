package com.joaolfp.popularmovies.data.repository

import com.joaolfp.popularmovies.vo.MoviesVO
import io.reactivex.Observable

interface MoviesRepository {
    fun getMovies(): Observable<List<MoviesVO>>
}