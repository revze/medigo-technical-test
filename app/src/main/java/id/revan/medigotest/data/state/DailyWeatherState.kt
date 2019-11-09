package id.revan.medigotest.data.state

import id.revan.medigotest.data.response.DailyWeatherResponse

sealed class DailyWeatherState {
    object Loading : DailyWeatherState()
    data class Success(val data: DailyWeatherResponse) : DailyWeatherState()
    data class Error(val statusCode: Int, val message: String) : DailyWeatherState()
}