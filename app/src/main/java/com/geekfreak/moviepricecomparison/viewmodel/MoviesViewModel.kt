package com.geekfreak.moviepricecomparison.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.*
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.*
import com.geekfreak.moviepricecomparison.MoviesApplication
import com.geekfreak.moviepricecomparison.model.MovieWithBothPrices
import com.geekfreak.moviepricecomparison.model.Movies
import com.geekfreak.moviepricecomparison.repository.MoviesRepository
import com.geekfreak.moviepricecomparison.util.Constants.Companion.CINEMA_WORLD
import com.geekfreak.moviepricecomparison.util.Constants.Companion.CONVERSION_ERROR_MESSAGE
import com.geekfreak.moviepricecomparison.util.Constants.Companion.FILM_WORLD
import com.geekfreak.moviepricecomparison.util.Constants.Companion.NETWORK_FAILURE_MESSAGE
import com.geekfreak.moviepricecomparison.util.Constants.Companion.NO_INTERNET_MESSAGE
import com.geekfreak.moviepricecomparison.util.Resource
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.BiFunction
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.IOException

class MoviesViewModel(app: Application, private val moviesRepository: MoviesRepository)
   :LifecycleObserver, AndroidViewModel(app)  {
   val movies: MutableLiveData<Resource<List<MovieWithBothPrices>>> = MutableLiveData()

   init {
      safeApiCall()
   }

   fun refresh() {
      safeApiCall()
   }

   private fun safeApiCall() {
      movies.postValue(Resource.Loading())
      try {
         if(hasInternetConnection()) {
            makeApiCall()
         } else {
            movies.postValue(Resource.Error(NO_INTERNET_MESSAGE))
         }
      } catch (t: Throwable) {
         when (t) {
            is IOException -> movies.postValue(Resource.Error(NETWORK_FAILURE_MESSAGE))
            else -> movies.postValue(Resource.Error(CONVERSION_ERROR_MESSAGE))
         }
      }
   }

   private fun makeApiCall() {
      Observable.zip(
      getFilmWorldMoviesObservable(),
      getCinemaWorldMoviesObservable(),
      BiFunction
      { filmWorldMovies, cinemaWorldMovies ->
         movies.postValue(Resource.Loading())
         return@BiFunction combineBoth(filmWorldMovies, cinemaWorldMovies)
      })
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .retry(3)
      .onErrorComplete { throwable ->
         throwable?.let { throwable ->
            movies.postValue(throwable.message?.let { message -> Resource.Error(message) })
         }
         return@onErrorComplete true
      }
      .subscribe {
         it?.let { movies ->
            this.movies.postValue(Resource.Success(movies))
         }
      }
   }

   private fun combineBoth(filmWorldMovies: Movies, cinemaWorldMovies: Movies)
   : List<MovieWithBothPrices> {
         val movieWithBothPrices = ArrayList<MovieWithBothPrices>()
         filmWorldMovies.movies.forEach { filmMovie ->
            val id = filmMovie.ID.drop(2)
            val cinemaMovie = cinemaWorldMovies.movies.find { cinemaMovie ->
               cinemaMovie.ID.contains( id, true ) }
            cinemaMovie?.let {
               movieWithBothPrices.add(
                  MovieWithBothPrices(id,
                     filmMovie.Title,
                     filmMovie.Type,
                     filmMovie.Poster,
                     filmMovie.Actors,
                     filmMovie.Price,
                     cinemaMovie.Price)
               )
            }
         }
         return movieWithBothPrices
   }

   private fun getFilmWorldMoviesObservable(): Observable<Movies> {
      return moviesRepository.getMovies(FILM_WORLD)
   }

   private fun getCinemaWorldMoviesObservable(): Observable<Movies> {
      return moviesRepository.getMovies(CINEMA_WORLD)
   }

   private fun hasInternetConnection(): Boolean {
      val connectivityManager = getApplication<MoviesApplication>()
         .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
         val activeNetwork = connectivityManager.activeNetwork ?: return false
         val capabilities =
            connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
         return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
         }
      } else {
         connectivityManager.activeNetworkInfo?.run {
            return when(type) {
               TYPE_WIFI -> true
               TYPE_MOBILE -> true
               TYPE_ETHERNET -> true
               else -> false
            }
         }
      }

      return false
   }
}
