package com.joaolfp.popularmovies.data.repository

import com.joaolfp.popularmovies.data.MoviesApi
import com.joaolfp.popularmovies.data.mapper.MoviesMapper
import com.joaolfp.popularmovies.data.model.FilmDTO
import com.joaolfp.popularmovies.vo.MoviesVO
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class MoviesRepositoryImpl(
    private val api: MoviesApi,
    private val moviesMapper: MoviesMapper
) : MoviesRepository {

    override fun getMovies(): Observable<List<MoviesVO>> {
        return api.getMovies("ec33a5ee87834b72e09e9aaf60d4c9fc").subscribeOn(Schedulers.io())
            .map { it.results }.flatMapIterable { it }.cast(FilmDTO::class.java)
            .map { moviesMapper.mapper(it) }.toList().toObservable()
    }
}