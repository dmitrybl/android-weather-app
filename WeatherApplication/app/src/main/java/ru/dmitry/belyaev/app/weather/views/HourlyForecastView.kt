package ru.dmitry.belyaev.app.weather.views

import ru.dmitry.belyaev.app.weather.rest.model.forecast.ForecastWeatherModel

interface HourlyForecastView: BaseView {

    fun addHour(forecastWeatherModel: ForecastWeatherModel)

    fun setTimezone(timezone: Long)

}