package ru.dmitry.belyaev.app.weather

import ru.dmitry.belyaev.app.weather.rest.WeatherApi

class ApiUtils {

    companion object {

        const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
        const val API_KEY = "8c031df7dbac4a3bd29b48b1aee1bb52"

        val weatherApi: WeatherApi by lazy {
            WeatherApi.create()
        }

    }
}