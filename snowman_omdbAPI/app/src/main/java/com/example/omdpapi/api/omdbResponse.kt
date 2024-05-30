package com.example.omdpapi.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass



@JsonClass(generateAdapter = true)
data class CMovieResponse(
    @Json(name = "Search") val galleryItems: List<CMovie>,
)

@JsonClass(generateAdapter = true)
data class CMovie(
    @Json(name = "Title") val title: String,
    @Json(name = "Year") val year: String,
    @Json(name = "Poster") val poster: String

)