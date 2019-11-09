package id.revan.medigotest.helper

import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class TemperatureHelperTest {
    private lateinit var SUT: TemperatureHelper

    @Before
    fun setUp() {
        SUT = TemperatureHelper()
    }

    @Test
    fun convertKelvinToCelcius_kelvin_celciusReturned() {
        assertThat(SUT.convertKelvinToCelcius(300.13), `is`("26°"))
    }
}