package com.geekfreak.moviepricecomparison.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.geekfreak.moviepricecomparison.repository.MoviesRepository
import com.geekfreak.moviepricecomparison.viewmodel.MoviesViewModel
import java.lang.IllegalArgumentException

class MoviesViewModelFactory(val app: Application,
                           private val repository: MoviesRepository
): ViewModelProvider.Factory {
   override fun <T : ViewModel> create(modelClass: Class<T>): T {
      return if (modelClass.isAssignableFrom(MoviesViewModel::class.java)) {
         MoviesViewModel(app, this.repository) as T
      }
      else {
         throw IllegalArgumentException("ViewModel not found")
      }
   }
}