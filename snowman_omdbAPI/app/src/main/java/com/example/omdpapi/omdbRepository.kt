package com.example.omdpapi


import com.example.omdpapi.api.omdbApi
import com.example.omdpapi.api.GalleryItem
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
object omdbRepository {
    private val omdbApi: omdbApi

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://www.omdbapi.com/?i=tt3896198&apikey=4bf9fd98")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        omdbApi = retrofit.create()
    }

    suspend fun fetchPhotos(): List<GalleryItem> =
        omdbApi.fetchPhotos().photos.galleryItems
}


