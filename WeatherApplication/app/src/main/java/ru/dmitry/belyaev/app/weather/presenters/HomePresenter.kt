package ru.dmitry.belyaev.app.weather.presenters

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.dmitry.belyaev.app.weather.ApiUtils
import ru.dmitry.belyaev.app.weather.Utils
import ru.dmitry.belyaev.app.weather.base.BasePresenter
import ru.dmitry.belyaev.app.weather.fragments.HomeFragment
import ru.dmitry.belyaev.app.weather.rest.model.current.CurrentWeatherModel
import ru.dmitry.belyaev.app.weather.rest.model.forecast.ForecastWeatherModel

class HomePresenter : BasePresenter<HomeFragment>() {

    fun showWeatherByCity(cityName: String) {
        val midDayRange: IntRange = 12..14
        view.showProgress()

        val currentWeatherObservable = ApiUtils.weatherApi.getWeatherByCity(cityName)

        val forecastWeatherObservable = ApiUtils.weatherApi.getForecastByCity(cityName)
                .flatMap { forecastList ->
                    Observable.fromIterable(forecastList.forecast).filter {
                        val hours = Utils.getHours(it.dt + forecastList.city.timezone)
                        hours in midDayRange
                    }
                }

        subscribe(Observable.concat(currentWeatherObservable, forecastWeatherObservable)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnComplete {
                view.hideProgress()
            }
            .subscribe({
                when(it) {
                    is CurrentWeatherModel -> {
                        view.updateWeather(it)
                    }
                    is ForecastWeatherModel -> {
                        view.addDay(it)
                    }
                }

            }, {
                view.showError()
                view.showErrorMessage(it.message!!)
            }))

    }

}