package com.joaolfp.popularmovies.di

import com.joaolfp.popularmovies.ui.MoviesViewModel
import com.joaolfp.popularmovies.data.MoviesApi
import com.joaolfp.popularmovies.data.mapper.MoviesMapper
import com.joaolfp.popularmovies.data.repository.MoviesRepositoryImpl
import com.joaolfp.popularmovies.networking.ApiService
import com.joaolfp.popularmovies.networking.behaviors.HandleBehavior
import io.reactivex.schedulers.Schedulers
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

val appModule = Kodein.Module("AppModule") {

    bind() from singleton {
        ApiService.getClient(MoviesApi::class.java)
    }

    bind() from provider {
        MoviesRepositoryImpl(instance(), MoviesMapper())
    }

    bind() from provider {
        MoviesViewModel(instance(), HandleBehavior(Schedulers.io()))
    }
}