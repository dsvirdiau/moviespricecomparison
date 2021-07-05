package com.geekfreak.moviepricecomparison.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.geekfreak.moviepricecomparison.R
import com.geekfreak.moviepricecomparison.databinding.ListViewItemBinding
import com.geekfreak.moviepricecomparison.model.MovieWithBothPrices
import java.util.*

class MoviesAdapter: RecyclerView.Adapter<MoviesViewHolder>(){
   private var movieList = mutableListOf<MovieWithBothPrices>()

   fun setMovieList(movieList: List<MovieWithBothPrices>) {
      this.movieList = movieList.toMutableList()
      notifyDataSetChanged()
   }

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
      val inflater = LayoutInflater.from(parent.context)
      val binding = ListViewItemBinding.inflate(inflater, parent, false)
      return MoviesViewHolder(binding)
   }

   override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
      val movie = movieList[position]
      holder.binding.tvMovieTitle.text = movie.Title
      holder.binding.tvActors.text = movie.Actors

      val filmWorldPrice = "Filmworld: " + formatPrice(movie.FilmWorldPrice)
      val cinemaWorldPrice = "Cinemaworld: " + formatPrice(movie.CinemaWorldPrice)
      holder.binding.tvFilmWorldPrice.text = filmWorldPrice
      holder.binding.tvCinemaWorldPrice.text = cinemaWorldPrice

      if(movie.FilmWorldPrice < movie.CinemaWorldPrice) {
         holder.binding.tvFilmWorldPrice.setBackgroundColor(Color.MAGENTA)
         holder.binding.tvCinemaWorldPrice.setBackgroundColor(Color.TRANSPARENT)
      } else {
         holder.binding.tvCinemaWorldPrice.setBackgroundColor(Color.MAGENTA)
         holder.binding.tvFilmWorldPrice.setBackgroundColor(Color.TRANSPARENT)
      }

      Glide.with(holder.binding.ivPoster.context)
         .load(movie.Poster)
         .error(R.drawable.ic_image_broken_variant)
         .fallback(R.drawable.ic_image)
         .into(holder.binding.ivPoster)
   }

   override fun getItemCount() = movieList.size

   private fun formatPrice( price: Double): String {
      val currency = Currency.getInstance(Locale.getDefault())
      return "${currency.symbol}$price"
   }
}

class MoviesViewHolder(val binding: ListViewItemBinding): RecyclerView.ViewHolder(binding.root)