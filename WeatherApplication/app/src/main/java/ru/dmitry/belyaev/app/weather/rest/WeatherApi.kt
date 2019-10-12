package ru.dmitry.belyaev.app.weather.rest

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import ru.dmitry.belyaev.app.weather.ApiUtils.Companion.API_KEY
import ru.dmitry.belyaev.app.weather.ApiUtils.Companion.BASE_URL
import ru.dmitry.belyaev.app.weather.rest.model.current.CurrentWeatherModel
import ru.dmitry.belyaev.app.weather.rest.model.forecast.ForecastList
import ru.dmitry.belyaev.app.weather.rest.model.forecast.ForecastWeatherModel

interface WeatherApi {

    companion object {

        fun create(): WeatherApi {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(WeatherApi::class.java)
        }
    }


    @GET("weather")
    fun getWeatherByCity(@Query("q") cityName: String,
                         @Query("appid") apiKey: String = API_KEY,
                         @Query("lang") lang: String = "ru"): Observable<CurrentWeatherModel>


    @GET("forecast")
    fun getForecastByCity(@Query("q") cityName: String,
                          @Query("appid") apiKey: String = API_KEY,
                          @Query("lang") lang: String = "ru"): Observable<ForecastList>

}