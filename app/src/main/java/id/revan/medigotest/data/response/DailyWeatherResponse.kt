package id.revan.medigotest.data.response

import com.google.gson.annotations.SerializedName
import id.revan.medigotest.data.model.DailyForecast
import id.revan.medigotest.data.model.Temperature
import id.revan.medigotest.data.model.Weather

data class DailyWeatherResponse (
    @SerializedName("list")
    val forecasts: List<DailyForecast>
)