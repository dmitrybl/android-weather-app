package ru.dmitry.belyaev.app.weather.rest.model.forecast

import com.google.gson.annotations.SerializedName

data class ForecastList(
    @SerializedName("list")
    val forecast: List<ForecastWeatherModel>,
    @SerializedName("city")
    val city: City
)