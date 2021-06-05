package io.android.momobill.data.service

import io.android.momobill.data.model.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("discover/movie")
    suspend fun getMovies(@Query("api_key") apiKey: String): MoviesResponse
}