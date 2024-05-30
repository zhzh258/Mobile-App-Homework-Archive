package com.example.omdpapi.api

import retrofit2.http.GET

private const val API_KEY = "4bf9fd98"

interface omdbApi {
    @GET(
        "services/rest/?method=omdb.interestingness.getList" +
            "&api_key=$API_KEY" +
            "&format=json" +
            "&nojsoncallback=1" +
            "&extras=url_s"
    )
    suspend fun fetchPhotos(): omdbResponse {

    }
}
