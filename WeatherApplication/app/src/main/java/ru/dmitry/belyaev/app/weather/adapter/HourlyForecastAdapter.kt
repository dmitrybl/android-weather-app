package ru.dmitry.belyaev.app.weather.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_hourly_forecast.view.*
import ru.dmitry.belyaev.app.weather.R
import ru.dmitry.belyaev.app.weather.adapter.model.HourlyForecast

class HourlyForecastAdapter: BaseAdapter<HourlyForecast>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyForecastViewHolder {
        return HourlyForecastViewHolder(LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_hourly_forecast, parent, false))
    }

    class HourlyForecastViewHolder(itemView: View): BaseViewHolder<HourlyForecast>(itemView) {

        override fun bind(model: HourlyForecast) {
            itemView.icon.setImageResource(model.icon)
            itemView.datetime.text = model.datetime
            itemView.city.text = model.city
            itemView.description.text = model.description
            itemView.temperature.text = model.temperature
        }

    }

}