package com.joaolfp.popularmovies.networking.behaviors

sealed class ViewBehavior<out T>

object INITIAL : ViewBehavior<Nothing>()
object FINISH : ViewBehavior<Nothing>()

data class SUCCESS<VALUE>(val result: VALUE) : ViewBehavior<VALUE>()
data class ERROR(val errorBehavior: ErrorBehavior) : ViewBehavior<Nothing>()