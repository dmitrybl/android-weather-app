package ru.dmitry.belyaev.app.weather

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.SimpleDateFormat
import java.util.*

class Utils {

    companion object {

        fun getDateTime(dt: Long, pattern: String = "dd.MM.yyyy HH:mm:ss"): String {
            val date = Date(dt * 1000)
            val sdf = SimpleDateFormat(pattern, Locale.US)
            sdf.timeZone = TimeZone.getTimeZone("UTC")
            return sdf.format(date)
        }

        fun getHours(dt: Long): Int {
            val date = Date(dt * 1000)
            val sdf = SimpleDateFormat("HH", Locale.US)
            sdf.timeZone = TimeZone.getTimeZone("UTC")
            return sdf.format(date).toInt()
        }

        fun formatTemperature(temperature: Double): String {
            return "${BigDecimal(temperature - 273.15).setScale(1, RoundingMode.HALF_EVEN)} C"
        }

    }
}