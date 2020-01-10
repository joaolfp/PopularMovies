package com.joaolfp.popularmovies.data.model

import com.google.gson.annotations.SerializedName

data class ResultDTO(
    @SerializedName("results") val results: List<FilmDTO>
)