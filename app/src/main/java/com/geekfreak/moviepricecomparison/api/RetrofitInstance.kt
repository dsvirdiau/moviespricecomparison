package com.geekfreak.moviepricecomparison.api

import com.geekfreak.moviepricecomparison.util.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
   companion object {
      private val retrofit by lazy {
         val logging = HttpLoggingInterceptor()
         logging.setLevel(HttpLoggingInterceptor.Level.BODY)
         val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

         Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(client)
            .build()
      }

      val api by lazy {
         retrofit.create(MovieApi::class.java)
      }
   }
}