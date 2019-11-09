package id.revan.medigotest.data.model

import com.google.gson.annotations.SerializedName

data class Temperature (
    @SerializedName("temp")
    val temp: Double
)