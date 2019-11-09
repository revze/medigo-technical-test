package id.revan.medigotest.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import id.revan.medigotest.data.model.Temperature
import id.revan.medigotest.data.repository.WeatherRepository
import id.revan.medigotest.data.response.CurrentWeatherResponse
import id.revan.medigotest.data.response.DailyWeatherResponse
import id.revan.medigotest.domain.Output
import id.revan.medigotest.data.state.CurrentWeatherState
import id.revan.medigotest.data.state.DailyWeatherState
import id.revan.medigotest.utils.ApiErrorCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
class MainViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val LAT = -1.00
    private val LON = -2.00

    @Mock
    private lateinit var weatherRepository: WeatherRepository

    @Mock
    private lateinit var currentWeatherObserver: Observer<CurrentWeatherState>

    @Mock
    private lateinit var dailyWeatherObserver: Observer<DailyWeatherState>


    private val testDispatcher = TestCoroutineDispatcher()

    private lateinit var SUT: MainViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
        SUT = MainViewModel(weatherRepository)
        SUT.currentWeatherState.observeForever(currentWeatherObserver)
        SUT.dailyWeatherState.observeForever(dailyWeatherObserver)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun getCurrentWeather_success_SuccessStateReturned() = runBlocking {
        val result = CurrentWeatherResponse(mutableListOf(), Temperature(temp = 230.00))
        `when`(weatherRepository.getCurrentWeather(LAT, LON)).thenReturn(
            Output.Success(
                result
            )
        )
        SUT.getCurrentWeather(LAT, LON)
        verify(currentWeatherObserver).onChanged(CurrentWeatherState.Loading)
        verify(currentWeatherObserver).onChanged(
            CurrentWeatherState.Success(
                result
            )
        )
    }

    @Test
    fun getCurrentWeather_networkError_ErrorStateReturned() = runBlocking {
        `when`(weatherRepository.getCurrentWeather(LAT, LON)).thenReturn(
            Output.Error(
                ApiErrorCode.NETWORK, "Koneksi internet bermasalah"
            )
        )
        SUT.getCurrentWeather(LAT, LON)
        verify(currentWeatherObserver).onChanged(CurrentWeatherState.Loading)
        verify(currentWeatherObserver).onChanged(
            CurrentWeatherState.Error(
                ApiErrorCode.NETWORK, "Koneksi internet bermasalah"
            )
        )
    }

    @Test
    fun getCurrentWeather_otherError_ErrorStateReturned() {
        runBlocking {
            `when`(weatherRepository.getCurrentWeather(LAT, LON)).thenReturn(
                Output.Error(
                    400, "Bad request"
                )
            )
            SUT.getCurrentWeather(LAT, LON)
            verify(currentWeatherObserver).onChanged(CurrentWeatherState.Loading)
            verify(currentWeatherObserver).onChanged(
                CurrentWeatherState.Error(
                    400, "Bad request"
                )
            )
        }
    }

    @Test
    fun getDailyWeather_success_SuccessStateReturned() {
        runBlocking {
            `when`(weatherRepository.getDailyWeather(LAT, LON)).thenReturn(
                Output.Success(
                    DailyWeatherResponse(mutableListOf())
                )
            )
            SUT.getDailyWeather(LAT, LON)
            verify(dailyWeatherObserver).onChanged(DailyWeatherState.Loading)
            verify(dailyWeatherObserver).onChanged(
                DailyWeatherState.Success(
                    DailyWeatherResponse(mutableListOf())
                )
            )
        }
    }

    @Test
    fun getDailyWeather_networkError_ErrorStateReturned() {
        runBlocking {
            `when`(weatherRepository.getDailyWeather(LAT, LON)).thenReturn(
                Output.Error(
                    ApiErrorCode.NETWORK, "Koneksi internet bermasalah"
                )
            )
            SUT.getDailyWeather(LAT, LON)
            verify(dailyWeatherObserver).onChanged(DailyWeatherState.Loading)
            verify(dailyWeatherObserver).onChanged(
                DailyWeatherState.Error(
                    ApiErrorCode.NETWORK, "Koneksi internet bermasalah"
                )
            )
        }
    }

    @Test
    fun getDailyWeather_otherError_ErrorStateReturned() {
        runBlocking {
            `when`(weatherRepository.getDailyWeather(LAT, LON)).thenReturn(
                Output.Error(
                    401, "Token not found"
                )
            )
            SUT.getDailyWeather(LAT, LON)
            verify(dailyWeatherObserver).onChanged(DailyWeatherState.Loading)
            verify(dailyWeatherObserver).onChanged(
                DailyWeatherState.Error(
                    401, "Token not found"
                )
            )
        }
    }

}