package com.joaolfp.popularmovies.data.mapper

import com.joaolfp.popularmovies.data.model.FilmDTO
import com.joaolfp.popularmovies.vo.MoviesVO
import org.junit.Before

import org.junit.Assert.assertEquals
import org.junit.Test

class MoviesMapperTest {

    companion object {
        private const val title = "Ip Man"
        private const val poster = "\\/yJdeWaVXa2se9agI6B4mQunVYkB.jpg"
    }

    private lateinit var moviesItems: FilmDTO
    private lateinit var result: MoviesVO

    @Before
    fun `before each test` () {
        moviesItems = FilmDTO(title, poster)
        result = MoviesMapper().mapper(moviesItems)
    }

    @Test
    fun `get movie title when mapper should have correct`() {
        assertEquals("Ip Man", result.title)
    }

    @Test
    fun `get movie path when mapper should have correct`() {
        assertEquals("\\/yJdeWaVXa2se9agI6B4mQunVYkB.jpg", result.poster_path)
    }
}