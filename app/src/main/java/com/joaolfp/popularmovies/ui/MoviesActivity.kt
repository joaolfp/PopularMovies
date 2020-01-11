package com.joaolfp.popularmovies.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.joaolfp.popularmovies.R
import com.joaolfp.popularmovies.networking.behaviors.INITIAL
import com.joaolfp.popularmovies.networking.behaviors.FINISH
import com.joaolfp.popularmovies.networking.behaviors.SUCCESS
import com.joaolfp.popularmovies.networking.behaviors.ERROR
import com.joaolfp.popularmovies.networking.behaviors.ErrorBehavior
import com.joaolfp.popularmovies.vo.MoviesVO
import kotlinx.android.synthetic.main.activity_movies.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class MoviesActivity : AppCompatActivity(), KodeinAware {

    override val kodein: Kodein by closestKodein()

    private val viewModel: MoviesViewModel by instance()

    private lateinit var adapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        setupFetchMovies()

        setupObservers()
    }

    private fun setupFetchMovies() {
        viewModel.fetchMovies()
    }

    private fun setupObservers() {
        viewModel.viewBehavior.observe(this, Observer { listMovies ->
            when (listMovies) {
                INITIAL -> initialLoading()
                FINISH -> finishLoading()
                is SUCCESS -> onSuccess(listMovies.result)
                is ERROR -> onError(listMovies.errorBehavior)
            }
        })
    }

    private fun onSuccess(moviesList: List<MoviesVO>) {
        setupRecyclerView(moviesList)
    }

    private fun onError(errorBehavior: ErrorBehavior) {
        Toast.makeText(baseContext, "" + errorBehavior, Toast.LENGTH_SHORT).show()
    }

    private fun initialLoading() {
        progressBar.visibility = View.VISIBLE
    }

    private fun finishLoading() {
        progressBar.visibility = View.GONE
    }

    private fun setupRecyclerView(moviesList: List<MoviesVO>) {

        listMovies.layoutManager = GridLayoutManager(this, 2)
        adapter = MoviesAdapter(moviesList)
        listMovies.adapter = adapter
    }
}
