package ru.dmitry.belyaev.app.weather.fragments

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.item_progress.*
import ru.dmitry.belyaev.app.weather.R
import ru.dmitry.belyaev.app.weather.Utils
import ru.dmitry.belyaev.app.weather.adapter.DailyForecastAdapter
import ru.dmitry.belyaev.app.weather.adapter.model.DayForecast
import ru.dmitry.belyaev.app.weather.base.BaseFragment
import ru.dmitry.belyaev.app.weather.presenters.HomePresenter
import ru.dmitry.belyaev.app.weather.rest.model.current.CurrentWeatherModel
import ru.dmitry.belyaev.app.weather.rest.model.forecast.ForecastWeatherModel
import ru.dmitry.belyaev.app.weather.views.HomeView
import android.content.Context
import android.view.inputmethod.InputMethodManager


class HomeFragment : BaseFragment(), HomeView {

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    private var timezoneOffset: Long = 0
    private lateinit var presenter: HomePresenter
    private lateinit var adapter: DailyForecastAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onRefresh() {
        super.onRefresh()
        adapter.clearItems()
        adapter.notifyDataSetChanged()
        presenter.showWeatherByCity(search.text.toString().trim())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        presenter = HomePresenter()
        presenter.attach(this)

        adapter = DailyForecastAdapter()

        dailyForecastList.also { rv ->
            rv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            rv.isNestedScrollingEnabled = false
            rv.adapter = adapter
        }

        swipeRefresh.setOnRefreshListener {
            onRefresh()
        }

        search.setOnEditorActionListener(object: TextView.OnEditorActionListener {
            override fun onEditorAction(tv: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEARCH
                    || actionId == EditorInfo.IME_ACTION_DONE
                    || event?.action == KeyEvent.ACTION_DOWN
                    && event.keyCode == KeyEvent.KEYCODE_ENTER) {

                    onRefresh()

                    val inputManager =
                        context!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputManager.toggleSoftInput(0, 0)
                    content_container.requestFocus()
                    return true
                }

                return false
            }

        })

        presenter.showWeatherByCity(search.text.toString().trim())

    }

    override fun showProgress() {
        progress.visibility = View.VISIBLE
        content.visibility = View.GONE
        error.visibility = View.GONE
    }

    override fun hideProgress() {
        progress.visibility = View.GONE
        content.visibility = View.VISIBLE
        error.visibility = View.GONE
    }

    override fun showError() {
        progress.visibility = View.GONE
        content.visibility = View.GONE
        error.visibility = View.VISIBLE
    }

    override fun showErrorMessage(message: String) {
       error.text = message
    }

    override fun updateWeather(modelCurrent: CurrentWeatherModel) {
        search.requestFocus()
        city.text = "${modelCurrent.name}, ${modelCurrent.sys.country}"
        degree.text = Utils.formatTemperature(modelCurrent.main.temp)
        humadity.text = "${modelCurrent.weather[0].description}, влажность: ${modelCurrent.main.humidity} %"
        timezoneOffset = modelCurrent.timezone
        Log.d("myLogs", "city: ${modelCurrent.name}")
    }

    override fun addDay(modelForecast: ForecastWeatherModel) {
        val dt = modelForecast.dt + timezoneOffset

        val dayForecast = DayForecast(
            Utils.getDateTime(dt, "dd.MM.yyyy"),
            Utils.getDateTime(dt,"HH:mm"),
            modelForecast.weather[0].description,
            Utils.formatTemperature(modelForecast.main.temp))

        adapter.addItem(dayForecast)
    }

    override fun onResume() {
        super.onResume()
        presenter.attach(this)
    }

    override fun onPause() {
        super.onPause()
        presenter.detach()
    }


}