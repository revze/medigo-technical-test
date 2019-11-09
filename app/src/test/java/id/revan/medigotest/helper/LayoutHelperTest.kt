package id.revan.medigotest.helper

import id.revan.medigotest.R
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class LayoutHelperTest {
    private lateinit var SUT: LayoutHelper

    @Before
    fun setUp() {
        SUT = LayoutHelper()
    }

    @Test
    fun getWeatherImage_clearSky_iconSunReturned() {
        assertThat(SUT.getWeatherImage("01d"), `is`(R.drawable.ic_sun))
    }

    @Test
    fun getWeatherImage_thunderStorm_iconCloudLightningReturned() {
        assertThat(SUT.getWeatherImage("11n"), `is`(R.drawable.ic_cloudlightning))
    }

    @Test
    fun getWeatherImage_showerRain_iconCloudRainReturned() {
        assertThat(SUT.getWeatherImage("09d"), `is`(R.drawable.ic_cloudrain))
    }

    @Test
    fun getWeatherImage_rain_iconCloudDrizzleReturned() {
        assertThat(SUT.getWeatherImage("10n"), `is`(R.drawable.ic_clouddrizzle))
    }

    @Test
    fun getWeatherImage_fewClouds_iconCloudCloudyReturned() {
        assertThat(SUT.getWeatherImage("02d"), `is`(R.drawable.ic_cloudcloudy))
    }

    @Test
    fun getWeatherImage_other_iconCloudCloudyReturned() {
        assertThat(SUT.getWeatherImage("50n"), `is`(R.drawable.ic_cloudcloudy))
    }
}