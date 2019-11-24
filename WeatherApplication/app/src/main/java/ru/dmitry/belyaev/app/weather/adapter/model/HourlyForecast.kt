package ru.dmitry.belyaev.app.weather.adapter.model

data class HourlyForecast(val icon: Int,
                          val datetime: String,
                          val description: String,
                          val temperature: String,
                          val city: String)