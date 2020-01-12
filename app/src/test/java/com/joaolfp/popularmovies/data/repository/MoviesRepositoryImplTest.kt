package com.joaolfp.popularmovies.data.repository

import com.joaolfp.popularmovies.BuildConfig
import com.joaolfp.popularmovies.data.MoviesApi
import com.joaolfp.popularmovies.data.mapper.MoviesMapper
import com.joaolfp.popularmovies.data.model.ResultDTO
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Observable
import org.junit.Before

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
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
        every { api.getMovies(BuildConfig.API_KEY) } returns Observable.just(
            ResultDTO(listOf())
        )

        repositoryImpl.getMovies().test().assertNoErrors()
    }
}