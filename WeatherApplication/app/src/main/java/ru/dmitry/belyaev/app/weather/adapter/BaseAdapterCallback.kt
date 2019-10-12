package ru.dmitry.belyaev.app.weather.adapter

import android.view.View

interface BaseAdapterCallback<T> {
    fun onItemClick(model: T, view: View)
}