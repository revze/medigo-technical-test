package id.revan.medigotest.data.model

import com.google.gson.annotations.SerializedName

data class DailyTemperature (
    @SerializedName("temp")
    val temperature: Double
)