package id.revan.medigotest.data.repository

import id.revan.medigotest.data.response.CurrentWeatherResponse
import id.revan.medigotest.data.response.DailyWeatherResponse
import id.revan.medigotest.data.services.ApiService
import id.revan.medigotest.utils.Endpoint
import id.revan.medigotest.domain.Output
import id.revan.medigotest.utils.ApiErrorCode
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    WeatherRepository {
    override suspend fun getDailyWeather(lat: Double, lon: Double): Output<DailyWeatherResponse> {
        return try {
            val response = apiService.getDailyWeather(lat, lon, Endpoint.APP_ID, "id")
            Output.Success(response)
        } catch (e: IOException) {
            Output.Error(ApiErrorCode.NETWORK, "Koneksi internet bermasalah")
        } catch (e: HttpException) {
            Output.Error(e.code(), e.message())
        }
    }

    override suspend fun getCurrentWeather(
        lat: Double,
        lon: Double
    ): Output<CurrentWeatherResponse> {
        return try {
            val response = apiService.getCurrentWeather(lat, lon, Endpoint.APP_ID, "id")
            Output.Success(response)
        } catch (e: IOException) {
            Output.Error(ApiErrorCode.NETWORK, "Koneksi internet bermasalah")
        } catch (e: HttpException) {
            Output.Error(e.code(), e.message())
        }
    }
}