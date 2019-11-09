package id.revan.medigotest.helper

import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class DateTimeHelperTest {
    private lateinit var SUT: DateTimeHelper

    @Before
    fun setUp() {
        SUT = DateTimeHelper()
    }

    @Test
    fun getCurrentDate_currentDate_todayReturned() {
        assertThat(SUT.getCurrentDate(), `is`("Sabtu, 9 November 2019"))
    }

    @Test
    fun updateDateFormat_hourFormat_hourFormatReturned() {
        assertThat(SUT.updateDateFormat("2019-10-01 20:00:00", "HH:mm"), `is`("20:00"))
    }

    @Test
    fun updateDateFormat_dateFormat_dateFormatReturned() {
        assertThat(SUT.updateDateFormat("2019-05-19 18:00:00", "dd-MM-yyyy"), `is`("19-05-2019"))
    }

    @Test
    fun updateDateFormat_invalidDateFormat_emptyStringReturned() {
        assertThat(SUT.updateDateFormat("2019-05-19 invalid", "dd-MM-yyyy"), `is`(""))
    }
}