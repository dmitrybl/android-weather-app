package ru.dmitry.belyaev.app.weather.rest.model.current

data class WeatherItem(val icon: String = "",
                       val description: String = "",
                       val main: String = "",
                       val id: Int = 0)