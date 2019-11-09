package id.revan.medigotest.helper

import javax.inject.Inject

class TemperatureHelper @Inject constructor() {
    fun convertKelvinToCelcius(temperature: Double): String {
        return "${temperature.minus(273.15).toInt()}°"
    }
}