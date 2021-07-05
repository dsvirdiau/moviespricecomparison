package com.geekfreak.moviepricecomparison.api

import com.geekfreak.moviepricecomparison.model.Movies
import com.geekfreak.moviepricecomparison.util.Constants.Companion.API_TOKEN
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface MovieApi {
   @Headers("x-api-key: $API_TOKEN")
   @GET("{theatre}/movies")
   fun getMovies(@Path("theatre") theatre: String): Observable<Movies>
}