package com.joaolfp.popularmovies.data.model

import com.google.gson.annotations.SerializedName

data class FilmDTO(
    @SerializedName("title") val title: String,
    @SerializedName("poster_path") val poster: String
)