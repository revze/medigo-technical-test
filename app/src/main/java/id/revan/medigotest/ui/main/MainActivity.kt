package id.revan.medigotest.ui.main

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import id.revan.medigotest.R
import id.revan.medigotest.shared.view.DailyWeatherItem
import id.revan.medigotest.shared.view.HourlyWeatherItem
import id.revan.medigotest.data.state.CurrentWeatherState
import id.revan.medigotest.data.state.DailyWeatherState
import id.revan.medigotest.databinding.ActivityMainBinding
import id.revan.medigotest.di.Injector
import id.revan.medigotest.helper.DateTimeHelper
import id.revan.medigotest.helper.LayoutHelper
import id.revan.medigotest.helper.TemperatureHelper
import id.revan.medigotest.ui.base.BaseViewModelFactory
import id.revan.medigotest.data.binding.CurrentWeatherBinding
import id.revan.medigotest.shared.extensions.gone
import id.revan.medigotest.shared.extensions.visible
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    @Inject
    lateinit var viewModelFactory: BaseViewModelFactory<MainViewModel>

    @Inject
    lateinit var hourAdapter: GroupAdapter<GroupieViewHolder>

    @Inject
    lateinit var dailyAdapter: GroupAdapter<GroupieViewHolder>

    @Inject
    lateinit var dateTimeHelper: DateTimeHelper

    @Inject
    lateinit var temperatureHelper: TemperatureHelper

    @Inject
    lateinit var layoutHelper: LayoutHelper

    private val LAT = -6.2502846
    private val LON = -106.8045059

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        Injector.getApp().inject(this)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.currentWeatherState.observe(this, currentWeatherObserver)
        viewModel.dailyWeatherState.observe(this, dailyWeatherObserver)

        btn_try_again_current_weather.setOnClickListener {
            viewModel.getCurrentWeather(LAT, LON)
        }

        rv_weather_hour.adapter = hourAdapter
        rv_weather_hour.layoutManager = GridLayoutManager(this, 5)
        rv_weather_hour.isNestedScrollingEnabled = false
        rv_daily_weather.adapter = dailyAdapter
        rv_daily_weather.layoutManager = LinearLayoutManager(this)
        rv_daily_weather.isNestedScrollingEnabled = false

        btn_try_again_forecast.setOnClickListener {
            viewModel.getDailyWeather(LAT, LON)
        }

        viewModel.getCurrentWeather(LAT, LON)
        viewModel.getDailyWeather(LAT, LON)
    }

    private val currentWeatherObserver = Observer<CurrentWeatherState> {
        when (it) {
            is CurrentWeatherState.Loading -> {
                layout_current_weather_loader.visible()
                layout_error_current_weather.gone()
                layout_current_weather.gone()
            }
            is CurrentWeatherState.Error -> {
                layout_current_weather_loader.gone()
                layout_error_current_weather.visible()
                layout_current_weather.gone()

                tv_error_current_weather.text = it.message
            }
            is CurrentWeatherState.Success -> {
                val response = it.data
                val weather = response.weathers[0]
                val temperature = response.temperature.temp

                layout_current_weather_loader.gone()
                layout_error_current_weather.gone()
                layout_current_weather.visible()

                binding.ivWeather.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        layoutHelper.getWeatherImage(weather.icon)
                    )
                )
                binding.currentWeather = CurrentWeatherBinding(
                    currentDate = dateTimeHelper.getCurrentDate(),
                    description = weather.description.capitalize(),
                    temperature = temperatureHelper.convertKelvinToCelcius(temperature)
                )
            }
        }
    }

    private val dailyWeatherObserver = Observer<DailyWeatherState> {
        when (it) {
            is DailyWeatherState.Loading -> {
                layout_forecast_loader.visible()
                layout_error_forecast.gone()
                layout_forecast.gone()
            }
            is DailyWeatherState.Error -> {
                layout_forecast_loader.gone()
                layout_error_forecast.visible()
                layout_forecast.gone()
                tv_error_forecast.text = it.message
            }
            is DailyWeatherState.Success -> {
                val response = it.data
                val weathers = response.forecasts
                val dailyWeatherHashMap = HashMap<String, String>()

                layout_forecast_loader.gone()
                layout_error_forecast.gone()
                layout_forecast.visible()

                hourAdapter.clear()
                weathers.subList(0, 5).map {
                    hourAdapter.add(
                        HourlyWeatherItem(
                            it,
                            dateTimeHelper,
                            temperatureHelper,
                            layoutHelper
                        )
                    )
                }
                dailyAdapter.clear()
                weathers.map {
                    val date = it.date.substring(0, 10)
                    if (!dailyWeatherHashMap.containsKey(date)) {
                        dailyWeatherHashMap[date] = date
                        dailyAdapter.add(
                            DailyWeatherItem(
                                it,
                                dateTimeHelper,
                                temperatureHelper,
                                layoutHelper
                            )
                        )
                    }
                }
            }
        }
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))
    }
}
