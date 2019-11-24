package ru.dmitry.belyaev.app.weather.adapter.model

data class DailyForecast(val icon: Int,
                         val day: String,
                         val date: String,
                         val time: String,
                         val description: String,
                         val temperature: String)