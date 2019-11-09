package id.revan.medigotest.data.model

import com.google.gson.annotations.SerializedName

data class DailyForecast (
    @SerializedName("dt")
    val timestamp: Long,

    @SerializedName("dt_txt")
    var date: String,

    @SerializedName("main")
    val temperature: DailyTemperature,

    @SerializedName("weather")
    val weathers: List<Weather>
)