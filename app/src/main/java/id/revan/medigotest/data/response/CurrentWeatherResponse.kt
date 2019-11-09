package id.revan.medigotest.data.response

import com.google.gson.annotations.SerializedName
import id.revan.medigotest.data.model.Temperature
import id.revan.medigotest.data.model.Weather

data class CurrentWeatherResponse (
    @SerializedName("weather")
    val weathers: List<Weather>,

    @SerializedName("main")
    val temperature: Temperature
)