package id.revan.medigotest.data.services

import id.revan.medigotest.data.response.CurrentWeatherResponse
import id.revan.medigotest.data.response.DailyWeatherResponse
import id.revan.medigotest.utils.Endpoint
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(Endpoint.CURRENT_WEATHER)
    suspend fun getCurrentWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appId: String,
        @Query("lang") lang: String
    ): CurrentWeatherResponse

    @GET(Endpoint.DAILY_WEATHER)
    suspend fun getDailyWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appId: String,
        @Query("lang") lang: String
    ): DailyWeatherResponse

    companion object {
        fun create(): ApiService {
            return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Endpoint.BASE_URL)
                .build().create(ApiService::class.java)
        }
    }
}