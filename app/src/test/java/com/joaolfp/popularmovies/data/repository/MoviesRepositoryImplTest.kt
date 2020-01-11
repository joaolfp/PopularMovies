package com.joaolfp.popularmovies.data.repository

import com.joaolfp.popularmovies.data.MoviesApi
import com.joaolfp.popularmovies.data.mapper.MoviesMapper
import com.joaolfp.popularmovies.data.model.ResultDTO
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Observable
import org.junit.Before

import org.junit.Test

class MoviesRepositoryImplTest {

    private lateinit var api: MoviesApi
    private lateinit var repositoryImpl: MoviesRepositoryImpl

    @Before
    fun `before each test`() {
        api = mockk()
        repositoryImpl = MoviesRepositoryImpl(api, MoviesMapper())
    }

    @Test
    fun `when success should appear no errors`() {
        every { api.getMovies("ec33a5ee87834b72e09e9aaf60d4c9fc") } returns Observable.just(
            ResultDTO(listOf())
        )

        repositoryImpl.getMovies().test().assertNoErrors()
    }
}