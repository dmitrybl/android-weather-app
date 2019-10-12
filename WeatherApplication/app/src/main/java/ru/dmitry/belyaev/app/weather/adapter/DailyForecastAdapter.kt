package ru.dmitry.belyaev.app.weather.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_daily_forecast.view.*
import ru.dmitry.belyaev.app.weather.R
import ru.dmitry.belyaev.app.weather.adapter.model.DayForecast

class DailyForecastAdapter : BaseAdapter<DayForecast>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyForecastViewHolder {
        return DailyForecastViewHolder(LayoutInflater
            .from(parent.context)
            .inflate(R.layout.fragment_daily_forecast, parent, false))
    }

    class DailyForecastViewHolder(itemView: View): BaseViewHolder<DayForecast>(itemView) {

        override fun bind(model: DayForecast) {
            itemView.date.text = model.date
            itemView.time.text = model.time
            itemView.description.text = model.description
            itemView.temperature.text = model.temperature
        }

    }

}