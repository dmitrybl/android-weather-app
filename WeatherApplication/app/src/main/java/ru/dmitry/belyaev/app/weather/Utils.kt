package ru.dmitry.belyaev.app.weather

import android.graphics.drawable.Drawable
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
            val value = BigDecimal(temperature - 273.15).setScale(1, RoundingMode.HALF_EVEN).toDouble()
            val sb = StringBuilder()
            if(value > 0) {
                sb.append("+ ")
            }
            sb.append(value)
            sb.append(" \u2103")
            return sb.toString()
        }

        fun getIconNameResource(name: String): Int {
            return when(name) {
                "01d" -> R.drawable.ic_01d
                "01n" -> R.drawable.ic_01n
                "02d" -> R.drawable.ic_02d
                "02n" -> R.drawable.ic_02n
                "03d" -> R.drawable.ic_03d
                "03n" -> R.drawable.ic_03n
                "04d" -> R.drawable.ic_04d
                "04n" -> R.drawable.ic_04n
                "09d" -> R.drawable.ic_09d
                "09n" -> R.drawable.ic_09n
                "10d" -> R.drawable.ic_10d
                "10n" -> R.drawable.ic_10n
                "11d" -> R.drawable.ic_11d
                "11n" -> R.drawable.ic_11n
                "13d" -> R.drawable.ic_13d
                "13n" -> R.drawable.ic_13n
                "50d" -> R.drawable.ic_50d
                "50n" -> R.drawable.ic_50n
                else -> R.drawable.ic_launcher_background
            }
        }

    }
}