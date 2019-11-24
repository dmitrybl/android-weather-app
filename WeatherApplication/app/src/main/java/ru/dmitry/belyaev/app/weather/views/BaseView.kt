package ru.dmitry.belyaev.app.weather.views

interface BaseView {

    fun showProgress()

    fun hideProgress()

    fun showError()

    fun showErrorMessage(message: String)
}