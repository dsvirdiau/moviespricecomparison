package com.geekfreak.moviepricecomparison.model

data class MovieWithBothPrices(
   val ID: String,
   val Title: String,
   val Type: String,
   val Poster: String,
   val Actors: String,
   val FilmWorldPrice: Double,
   val CinemaWorldPrice: Double,
)
