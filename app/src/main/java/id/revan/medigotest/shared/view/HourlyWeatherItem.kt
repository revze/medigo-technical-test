package id.revan.medigotest.shared.view

import androidx.core.content.ContextCompat
import com.xwray.groupie.databinding.BindableItem
import id.revan.medigotest.R
import id.revan.medigotest.data.model.DailyForecast
import id.revan.medigotest.databinding.ItemRowWeatherHourBinding
import id.revan.medigotest.helper.DateTimeHelper
import id.revan.medigotest.helper.LayoutHelper
import id.revan.medigotest.helper.TemperatureHelper

class HourlyWeatherItem(
    private val dailyForecast: DailyForecast,
    private val dateTimeHelper: DateTimeHelper,
    private val temperatureHelper: TemperatureHelper,
    private val layoutHelper: LayoutHelper
) : BindableItem<ItemRowWeatherHourBinding>() {

    override fun bind(binding: ItemRowWeatherHourBinding, position: Int) {
        val temperature = dailyForecast.temperature
        val icon = dailyForecast.weathers[0].icon

        binding.tvHour.text = dateTimeHelper.updateDateFormat(dailyForecast.date, "KKa")
        binding.ivWeather.setImageDrawable(ContextCompat.getDrawable(binding.root.context, layoutHelper.getWeatherImage(icon)))
        binding.tvTemperature.text =
            temperatureHelper.convertKelvinToCelcius(temperature.temperature)
    }

    override fun getLayout(): Int = R.layout.item_row_weather_hour
}