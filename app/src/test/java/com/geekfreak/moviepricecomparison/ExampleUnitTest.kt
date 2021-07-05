package com.geekfreak.moviepricecomparison

import com.geekfreak.moviepricecomparison.model.Movies
import com.geekfreak.moviepricecomparison.repository.MoviesRepository
import com.geekfreak.moviepricecomparison.util.Constants.Companion.FILM_WORLD
import com.geekfreak.moviepricecomparison.viewmodel.MoviesViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.observers.TestObserver
import org.junit.Test
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

   @Rule
   @JvmField
   val rule = MockitoJUnit.rule()!!

   @Rule @JvmField var testSchedulerRule = RxSchedulerRule()

   lateinit var mockData: MockMoviesData

   @Mock
   lateinit var repository: MoviesRepository

   @Mock
   lateinit var viewModel: MoviesViewModel

   @Before
   fun setup() {
      repository = Mockito.mock(MoviesRepository::class.java)
      viewModel = Mockito.mock(MoviesViewModel::class.java)
      mockData = MockMoviesData()
   }

   @Test
   fun `when film world movies are requested should call repository and return response`() {
      val response = mockData.getFilmWorldMovies()

      Mockito
         .`when`(repository.getMovies(FILM_WORLD))
         .thenReturn(Observable.just(response))

      val result = repository.getMovies(FILM_WORLD)

      val testObserver = TestObserver<Movies>()

      result.subscribe( testObserver )

      testObserver.assertComplete()
      testObserver.assertNoErrors()
      testObserver.assertValueCount(1)

      val listResult = testObserver.values()[0]
      assert(listResult.movies.size == 11)
   }

   @Test
   fun `when cinema world movies are requested should call repository and return response`() {
      val response = mockData.getCinemaWorldMovies()

      Mockito
         .`when`(repository.getMovies(FILM_WORLD))
         .thenReturn(Observable.just(response))

      val result = repository.getMovies(FILM_WORLD)

      val testObserver = TestObserver<Movies>()

      result.subscribe( testObserver )

      testObserver.assertComplete()
      testObserver.assertNoErrors()
      testObserver.assertValueCount(1)

      val listResult = testObserver.values()[0]
      assert(listResult.movies.size == 11)
   }
}