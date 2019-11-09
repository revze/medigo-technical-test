package id.revan.medigotest.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.revan.medigotest.data.repository.WeatherRepository
import id.revan.medigotest.domain.Output
import id.revan.medigotest.data.state.CurrentWeatherState
import id.revan.medigotest.data.state.DailyWeatherState
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val weatherRepository: WeatherRepository) :
    ViewModel() {
    val currentWeatherState = MutableLiveData<CurrentWeatherState>()
    val dailyWeatherState = MutableLiveData<DailyWeatherState>()

    fun getCurrentWeather(lat: Double, lon: Double) {
        currentWeatherState.value = CurrentWeatherState.Loading

        viewModelScope.launch {
            val result = weatherRepository.getCurrentWeather(lat, lon)

            when (result) {
                is Output.Success -> currentWeatherState.postValue(
                    CurrentWeatherState.Success(result.output)
                )
                is Output.Error -> currentWeatherState.postValue(
                    CurrentWeatherState.Error(
                        result.code,
                        result.message
                    )
                )
            }
        }
    }

    fun getDailyWeather(lat: Double, lon: Double) {
        dailyWeatherState.value = DailyWeatherState.Loading

        viewModelScope.launch {
            val result = weatherRepository.getDailyWeather(lat, lon)

            when (result) {
                is Output.Success -> dailyWeatherState.postValue(
                    DailyWeatherState.Success(result.output)
                )
                is Output.Error -> dailyWeatherState.postValue(
                    DailyWeatherState.Error(
                        result.code,
                        result.message
                    )
                )
            }
        }
    }
}