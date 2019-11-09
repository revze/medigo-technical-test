package id.revan.medigotest.data.state

import id.revan.medigotest.data.response.CurrentWeatherResponse

sealed class CurrentWeatherState {
    object Loading : CurrentWeatherState()
    data class Success(val data: CurrentWeatherResponse) : CurrentWeatherState()
    data class Error(val statusCode: Int, val message: String) : CurrentWeatherState()
}