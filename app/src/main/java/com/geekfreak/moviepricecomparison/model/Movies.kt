package com.geekfreak.moviepricecomparison.model

import com.google.gson.annotations.SerializedName

data class Movies(
   @SerializedName("Movies")
   val movies: List<Movie>,
   @SerializedName("Provider")
   val provider: String
)
