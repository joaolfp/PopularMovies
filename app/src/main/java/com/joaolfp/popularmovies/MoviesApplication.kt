package com.joaolfp.popularmovies

import android.app.Application
import com.joaolfp.popularmovies.di.appModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware

class MoviesApplication : Application(), KodeinAware {
    override val kodein = Kodein {
        import(appModule)
    }
}