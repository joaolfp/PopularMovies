package com.joaolfp.popularmovies.networking.behaviors

import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.Scheduler

class HandleBehavior<T>(
    private val scheduler: Scheduler
) : ObservableTransformer<T, ViewBehavior<T>> {

    override fun apply(upstream: Observable<T>): Observable<ViewBehavior<T>> {
        return upstream
            .map { value: T -> SUCCESS(value) as ViewBehavior<T> }
            .onErrorReturn { error: Throwable -> ERROR(ErrorBehavior(error)) }
            .startWith(INITIAL).concatWith(Observable.just(FINISH)).observeOn(scheduler)
    }
}