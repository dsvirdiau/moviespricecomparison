package com.geekfreak.moviepricecomparison

import com.geekfreak.moviepricecomparison.model.Movie
import com.geekfreak.moviepricecomparison.model.Movies
import com.geekfreak.moviepricecomparison.repository.MoviesRepository
import com.google.gson.Gson

class MockMoviesRepository {
   val filmWorldMoviesJson = "{\n" +
           "\t\"Provider\": \"Film World\",\n" +
           "\t\"Movies\": [\n" +
           "\t\t{\n" +
           "\t\t\t\"ID\": \"fw2488496\",\n" +
           "\t\t\t\"Title\": \"Star Wars: Episode VII - The Force Awakens\",\n" +
           "\t\t\t\"Type\": \"movie\",\n" +
           "\t\t\t\"Poster\": \"https://m.media-amazon.com/images/M/MV5BOTAzODEzNDAzMl5BMl5BanBnXkFtZTgwMDU1MTgzNzE@._V1_SX300.jpg\",\n" +
           "\t\t\t\"Actors\": \"Harrison Ford, Mark Hamill, Carrie Fisher, Adam Driver\",\n" +
           "\t\t\t\"Price\": 25\n" +
           "\t\t},\n" +
           "\t\t{\n" +
           "\t\t\t\"ID\": \"fw2527336\",\n" +
           "\t\t\t\"Title\": \"Star Wars: Episode VIII - The Last Jedi\",\n" +
           "\t\t\t\"Type\": \"movie\",\n" +
           "\t\t\t\"Poster\": \"https://m.media-amazon.com/images/M/MV5BMjQ1MzcxNjg4N15BMl5BanBnXkFtZTgwNzgwMjY4MzI@._V1_SX300.jpg\",\n" +
           "\t\t\t\"Actors\": \"Mark Hamill, Carrie Fisher, Adam Driver, Daisy Ridley\",\n" +
           "\t\t\t\"Price\": 24.5\n" +
           "\t\t},\n" +
           "\t\t{\n" +
           "\t\t\t\"ID\": \"fw2527338\",\n" +
           "\t\t\t\"Title\": \"Star Wars: Episode IX - The Rise of Skywalker\",\n" +
           "\t\t\t\"Type\": \"movie\",\n" +
           "\t\t\t\"Poster\": \"https://m.media-amazon.com/images/M/MV5BMDljNTQ5ODItZmQwMy00M2ExLTljOTQtZTVjNGE2NTg0NGIxXkEyXkFqcGdeQXVyODkzNTgxMDg@._V1_SX300.jpg\",\n" +
           "\t\t\t\"Actors\": \"Carrie Fisher, Mark Hamill, Adam Driver, Daisy Ridley\",\n" +
           "\t\t\t\"Price\": 23.5\n" +
           "\t\t},\n" +
           "\t\t{\n" +
           "\t\t\t\"ID\": \"fw3748528\",\n" +
           "\t\t\t\"Title\": \"Rogue One: A Star Wars Story\",\n" +
           "\t\t\t\"Type\": \"movie\",\n" +
           "\t\t\t\"Poster\": \"https://m.media-amazon.com/images/M/MV5BMjEwMzMxODIzOV5BMl5BanBnXkFtZTgwNzg3OTAzMDI@._V1_SX300.jpg\",\n" +
           "\t\t\t\"Actors\": \"Felicity Jones, Diego Luna, Alan Tudyk, Donnie Yen\",\n" +
           "\t\t\t\"Price\": 28\n" +
           "\t\t},\n" +
           "\t\t{\n" +
           "\t\t\t\"ID\": \"fw3778644\",\n" +
           "\t\t\t\"Title\": \"Solo: A Star Wars Story\",\n" +
           "\t\t\t\"Type\": \"movie\",\n" +
           "\t\t\t\"Poster\": \"https://m.media-amazon.com/images/M/MV5BOTM2NTI3NTc3Nl5BMl5BanBnXkFtZTgwNzM1OTQyNTM@._V1_SX300.jpg\",\n" +
           "\t\t\t\"Actors\": \"Alden Ehrenreich, Joonas Suotamo, Woody Harrelson, Emilia Clarke\",\n" +
           "\t\t\t\"Price\": 24\n" +
           "\t\t},\n" +
           "\t\t{\n" +
           "\t\t\t\"ID\": \"fw0076759\",\n" +
           "\t\t\t\"Title\": \"Star Wars: Episode IV - A New Hope\",\n" +
           "\t\t\t\"Type\": \"movie\",\n" +
           "\t\t\t\"Poster\": \"https://m.media-amazon.com/images/M/MV5BNzVlY2MwMjktM2E4OS00Y2Y3LWE3ZjctYzhkZGM3YzA1ZWM2XkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SX300.jpg\",\n" +
           "\t\t\t\"Actors\": \"Mark Hamill, Harrison Ford, Carrie Fisher, Peter Cushing\",\n" +
           "\t\t\t\"Price\": 22.9\n" +
           "\t\t},\n" +
           "\t\t{\n" +
           "\t\t\t\"ID\": \"fw0080684\",\n" +
           "\t\t\t\"Title\": \"Star Wars: Episode V - The Empire Strikes Back\",\n" +
           "\t\t\t\"Type\": \"movie\",\n" +
           "\t\t\t\"Poster\": \"https://m.media-amazon.com/images/M/MV5BYmU1NDRjNDgtMzhiMi00NjZmLTg5NGItZDNiZjU5NTU4OTE0XkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SX300.jpg\",\n" +
           "\t\t\t\"Actors\": \"Mark Hamill, Harrison Ford, Carrie Fisher, Billy Dee Williams\",\n" +
           "\t\t\t\"Price\": 23.7\n" +
           "\t\t},\n" +
           "\t\t{\n" +
           "\t\t\t\"ID\": \"fw0086190\",\n" +
           "\t\t\t\"Title\": \"Star Wars: Episode VI - Return of the Jedi\",\n" +
           "\t\t\t\"Type\": \"movie\",\n" +
           "\t\t\t\"Poster\": \"https://m.media-amazon.com/images/M/MV5BOWZlMjFiYzgtMTUzNC00Y2IzLTk1NTMtZmNhMTczNTk0ODk1XkEyXkFqcGdeQXVyNTAyODkwOQ@@._V1_SX300.jpg\",\n" +
           "\t\t\t\"Actors\": \"Mark Hamill, Harrison Ford, Carrie Fisher, Billy Dee Williams\",\n" +
           "\t\t\t\"Price\": 22\n" +
           "\t\t},\n" +
           "\t\t{\n" +
           "\t\t\t\"ID\": \"fw0120915\",\n" +
           "\t\t\t\"Title\": \"Star Wars: Episode I - The Phantom Menace\",\n" +
           "\t\t\t\"Type\": \"movie\",\n" +
           "\t\t\t\"Poster\": \"https://m.media-amazon.com/images/M/MV5BYTRhNjcwNWQtMGJmMi00NmQyLWE2YzItODVmMTdjNWI0ZDA2XkEyXkFqcGdeQXVyNTAyODkwOQ@@._V1_SX300.jpg\",\n" +
           "\t\t\t\"Actors\": \"Liam Neeson, Ewan McGregor, Natalie Portman, Jake Lloyd\",\n" +
           "\t\t\t\"Price\": 27.2\n" +
           "\t\t},\n" +
           "\t\t{\n" +
           "\t\t\t\"ID\": \"fw0121765\",\n" +
           "\t\t\t\"Title\": \"Star Wars: Episode II - Attack of the Clones\",\n" +
           "\t\t\t\"Type\": \"movie\",\n" +
           "\t\t\t\"Poster\": \"https://m.media-amazon.com/images/M/MV5BMDAzM2M0Y2UtZjRmZi00MzVlLTg4MjEtOTE3NzU5ZDVlMTU5XkEyXkFqcGdeQXVyNDUyOTg3Njg@._V1_SX300.jpg\",\n" +
           "\t\t\t\"Actors\": \"Ewan McGregor, Natalie Portman, Hayden Christensen, Christopher Lee\",\n" +
           "\t\t\t\"Price\": 19.9\n" +
           "\t\t},\n" +
           "\t\t{\n" +
           "\t\t\t\"ID\": \"fw0121766\",\n" +
           "\t\t\t\"Title\": \"Star Wars: Episode III - Revenge of the Sith\",\n" +
           "\t\t\t\"Type\": \"movie\",\n" +
           "\t\t\t\"Poster\": \"https://m.media-amazon.com/images/M/MV5BNTc4MTc3NTQ5OF5BMl5BanBnXkFtZTcwOTg0NjI4NA@@._V1_SX300.jpg\",\n" +
           "\t\t\t\"Actors\": \"Ewan McGregor, Natalie Portman, Hayden Christensen, Ian McDiarmid\",\n" +
           "\t\t\t\"Price\": 22.4\n" +
           "\t\t}\n" +
           "\t]\n" +
           "}"

   fun getFilmWorldMovies() = Gson().fromJson(filmWorldMoviesJson, Movies::class.java)
}