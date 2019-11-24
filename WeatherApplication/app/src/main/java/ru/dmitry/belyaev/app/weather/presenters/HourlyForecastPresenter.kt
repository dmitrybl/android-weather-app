package ru.dmitry.belyaev.app.weather.presenters

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.dmitry.belyaev.app.weather.ApiUtils
import ru.dmitry.belyaev.app.weather.Utils
import ru.dmitry.belyaev.app.weather.fragments.HourlyForecastFragment

class HourlyForecastPresenter : BasePresenter<HourlyForecastFragment>() {

    fun loadForecastForDayByCity(day: String, city: String) {

        view.showProgress()

        subscribe(ApiUtils.weatherApi.getForecastByCity(city)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                view.setTimezone(it.city.timezone)
            }
            .observeOn(Schedulers.io())
            .flatMap { forecastList ->
                Observable.fromIterable(forecastList.forecast).filter {
                    day.startsWith(Utils.getDateTime(it.dt + forecastList.city.timezone, "dd"))
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .doOnComplete {
                view.hideProgress()
            }
            .subscribe({
                view.addHour(it)
            }, {
                view.showError()
                view.showErrorMessage(it.message!!)
            }))

    }
}