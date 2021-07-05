package com.geekfreak.moviepricecomparison.repository

import com.geekfreak.moviepricecomparison.api.RetrofitInstance

class MoviesRepository {
   fun getMovies(theatre:String) = RetrofitInstance.api.getMovies(theatre)
}