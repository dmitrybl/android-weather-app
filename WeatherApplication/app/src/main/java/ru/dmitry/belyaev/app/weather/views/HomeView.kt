package ru.dmitry.belyaev.app.weather.views

import ru.dmitry.belyaev.app.weather.base.BaseView
import ru.dmitry.belyaev.app.weather.rest.model.current.CurrentWeatherModel
import ru.dmitry.belyaev.app.weather.rest.model.forecast.ForecastWeatherModel

interface HomeView : BaseView {

    fun updateWeather(modelCurrent: CurrentWeatherModel)

    fun addDay(modelForecast: ForecastWeatherModel)

    fun showErrorMessage(message: String)

}