package com.joaolfp.popularmovies.networking.behaviors

interface MapperInterface<in DTO, out VO> {
    fun mapper(parameter: DTO): VO
}