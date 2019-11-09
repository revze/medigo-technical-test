package id.revan.medigotest.data.repository

import id.revan.medigotest.data.response.CurrentWeatherResponse
import id.revan.medigotest.data.response.DailyWeatherResponse
import id.revan.medigotest.domain.Output

interface WeatherRepository {
    suspend fun getCurrentWeather(lat: Double, lon: Double): Output<CurrentWeatherResponse>
    suspend fun getDailyWeather(lat: Double, lon: Double): Output<DailyWeatherResponse>
}