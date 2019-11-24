package ru.dmitry.belyaev.app.weather.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_hourly_forecast.*
import kotlinx.android.synthetic.main.item_progress.*
import ru.dmitry.belyaev.app.weather.R
import ru.dmitry.belyaev.app.weather.Utils
import ru.dmitry.belyaev.app.weather.adapter.BaseAdapter
import ru.dmitry.belyaev.app.weather.adapter.HourlyForecastAdapter
import ru.dmitry.belyaev.app.weather.adapter.model.HourlyForecast
import ru.dmitry.belyaev.app.weather.presenters.HourlyForecastPresenter
import ru.dmitry.belyaev.app.weather.rest.model.forecast.ForecastWeatherModel
import ru.dmitry.belyaev.app.weather.views.HourlyForecastView

class HourlyForecastFragment : BaseListFragment<HourlyForecast>(), HourlyForecastView {

    companion object {
        const val DAY = "day"
        const val CITY = "city"

        fun newInstance(day: String, city: String): HourlyForecastFragment {
            val bundle = Bundle()
            val fragment = HourlyForecastFragment()

            bundle.putString(DAY, day)
            bundle.putString(CITY, city)

            fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var presenter: HourlyForecastPresenter
    private val day: String
        get() = arguments!!.getString(DAY, "")

    private val city: String
        get() = arguments!!.getString(CITY, "")

    private var timezoneOffset: Long = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_hourly_forecast, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = HourlyForecastPresenter()
        presenter.attach(this)
        presenter.loadForecastForDayByCity(day, city)
    }

    override fun onResume() {
        super.onResume()
        presenter.attach(this)
    }

    override fun onPause() {
        super.onPause()
        presenter.detach()
    }

    override fun onRefresh() {
        super.onRefresh()
        viewAdapter.clearItems()
        viewAdapter.notifyDataSetChanged()
        presenter.loadForecastForDayByCity(day, city)
    }

    override fun showProgress() {
        progress.visibility = View.VISIBLE
        list.visibility = View.GONE
        error.visibility = View.GONE
    }

    override fun hideProgress() {
        progress.visibility = View.GONE
        list.visibility = View.VISIBLE
        error.visibility = View.GONE
    }

    override fun showError() {
        progress.visibility = View.GONE
        list.visibility = View.GONE
        error.visibility = View.VISIBLE
    }

    override fun showErrorMessage(message: String) {
        error.text = message
    }

    override fun createAdapterInstance(): BaseAdapter<HourlyForecast> {
        return HourlyForecastAdapter()
    }

    override fun addHour(forecastWeatherModel: ForecastWeatherModel) {
        val dt = forecastWeatherModel.dt + timezoneOffset

        val hourlyForecast = HourlyForecast(
            Utils.getIconNameResource(forecastWeatherModel.weather[0].icon),
            Utils.getDateTime(dt, "dd.MM.yyyy, HH:mm"),
            "${forecastWeatherModel.weather[0].description}, влажность ${forecastWeatherModel.main.humidity} %",
            Utils.formatTemperature(forecastWeatherModel.main.temp),
            city)

        viewAdapter.addItem(hourlyForecast)

    }

    override fun setTimezone(timezone: Long) {
        timezoneOffset = timezone
    }

}