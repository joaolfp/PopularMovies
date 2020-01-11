package com.joaolfp.popularmovies.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.joaolfp.popularmovies.data.repository.MoviesRepository
import com.joaolfp.popularmovies.networking.behaviors.HandleBehavior
import com.joaolfp.popularmovies.networking.behaviors.ViewBehavior
import com.joaolfp.popularmovies.vo.MoviesVO
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.junit.Before

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MoviesViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var repository: MoviesRepository
    private lateinit var viewModel: MoviesViewModel

    @Before
    fun `before each test`() {
        repository = mockk()
        viewModel = MoviesViewModel(repository, HandleBehavior(Schedulers.trampoline()),
            Schedulers.trampoline())
    }

    @Test
    fun `when load movies soon transmit behavior`() {
        every { repository.getMovies() } returns Observable.just(listOf())

        val observer = mockk<Observer<in ViewBehavior<List<MoviesVO>>>>()

        viewModel.viewBehavior.observeForever(observer)

        viewModel.fetchMovies()

        verify { observer.onChanged(any()) }
    }
}