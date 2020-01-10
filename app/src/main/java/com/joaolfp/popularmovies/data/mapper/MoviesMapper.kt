package com.joaolfp.popularmovies.data.mapper

import com.joaolfp.popularmovies.data.model.FilmDTO
import com.joaolfp.popularmovies.networking.behaviors.MapperInterface
import com.joaolfp.popularmovies.vo.MoviesVO

class MoviesMapper : MapperInterface<FilmDTO, MoviesVO> {
    override fun mapper(parameter: FilmDTO): MoviesVO {
        return MoviesVO(
            title = parameter.title,
            poster_path = parameter.poster
        )
    }
}