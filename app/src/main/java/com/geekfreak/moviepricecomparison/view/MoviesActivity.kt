package com.geekfreak.moviepricecomparison.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.geekfreak.moviepricecomparison.adapter.MoviesAdapter
import com.geekfreak.moviepricecomparison.databinding.ActivityMoviesBinding
import com.geekfreak.moviepricecomparison.factory.MoviesViewModelFactory
import com.geekfreak.moviepricecomparison.repository.MoviesRepository
import com.geekfreak.moviepricecomparison.util.Constants.Companion.SPAN_COUNT
import com.geekfreak.moviepricecomparison.util.Constants.Companion.SWIPE_REFRESH_DELAY
import com.geekfreak.moviepricecomparison.util.Resource
import com.geekfreak.moviepricecomparison.viewmodel.MoviesViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private lateinit var binding: ActivityMoviesBinding
private lateinit var viewModel: MoviesViewModel
const val TAG = "MoviesActivity"
private val moviesAdapter = MoviesAdapter()

class MoviesActivity : AppCompatActivity() {
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      binding = ActivityMoviesBinding.inflate(layoutInflater)
      setContentView(binding.root)

      viewModel = ViewModelProvider(this,
         MoviesViewModelFactory(application, MoviesRepository()))
         .get(MoviesViewModel::class.java)

      binding.recyclerView.apply {
         layoutManager = GridLayoutManager(this@MoviesActivity, SPAN_COUNT,
            RecyclerView.VERTICAL, false)
         adapter = moviesAdapter
      }

      viewModel.movies.observe(this, {
         when (it) {
            is Resource.Loading -> {
               //show progress dialog maybe?
            }
            is Resource.Success -> {
               it.data?.let { movies ->
                  //set adapter
                  moviesAdapter.setMovieList(movies)
               }
            }
            is Resource.Error -> {
               it.message?.let { message ->
                  Log.e(TAG, "onCreate: $message")
                  Toast.makeText(this, message, Toast.LENGTH_LONG).show()
               }
            }
         }
      })

      binding.swipeRefreshRecyclerView.setOnRefreshListener {
         lifecycleScope.launch {
            viewModel.refresh()
            delay(SWIPE_REFRESH_DELAY)
            binding.swipeRefreshRecyclerView.isRefreshing = false
         }
      }
   }
}