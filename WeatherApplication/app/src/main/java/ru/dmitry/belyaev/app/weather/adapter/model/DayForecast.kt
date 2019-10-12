package ru.dmitry.belyaev.app.weather.adapter.model

data class DayForecast(val date: String,
                       val time: String,
                       val description: String,
                       val temperature: String)