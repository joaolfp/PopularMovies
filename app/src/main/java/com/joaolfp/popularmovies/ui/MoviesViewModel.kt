package com.joaolfp.popularmovies.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.joaolfp.popularmovies.data.repository.MoviesRepositoryImpl
import com.joaolfp.popularmovies.networking.behaviors.HandleBehavior
import com.joaolfp.popularmovies.networking.behaviors.SUCCESS
import com.joaolfp.popularmovies.networking.behaviors.ViewBehavior
import com.joaolfp.popularmovies.vo.MoviesVO
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

class MoviesViewModel(
    private val repositoryImpl: MoviesRepositoryImpl,
    private val handleBehavior: HandleBehavior<List<MoviesVO>>
) : ViewModel() {

    private var disposable: Disposable? = null

    private var movies = mutableListOf<MoviesVO>()

    private val _viewBehavior = MutableLiveData<ViewBehavior<List<MoviesVO>>>()
    val viewBehavior: LiveData<ViewBehavior<List<MoviesVO>>> = _viewBehavior

    fun fetchMovies() {
        disposable = repositoryImpl.getMovies().compose(handleBehavior)
            .observeOn(AndroidSchedulers.mainThread()).doAfterNext { listMovies ->
                if (listMovies is SUCCESS)
                    movies.addAll(listMovies.result)
            }.subscribe { listMovies -> _viewBehavior.value = listMovies }
    }

    override fun onCleared() {
        super.onCleared()

        disposable?.dispose()
    }
}