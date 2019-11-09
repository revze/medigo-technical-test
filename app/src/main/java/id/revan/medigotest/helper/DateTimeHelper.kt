package id.revan.medigotest.helper

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class DateTimeHelper @Inject constructor() {
    fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("EEEE, d MMMM yyyy", Locale("id"))
        val date = Date()
        return dateFormat.format(date)
    }

    fun updateDateFormat(date: String, newFormat: String): String {
        val oldFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale("id"))
        val newDateFormat = SimpleDateFormat(newFormat, Locale("id"))
        var result = ""

        try {
            val oldDate = oldFormat.parse(date)

            result = if (oldDate != null) newDateFormat.format(oldDate) else ""
        } catch (e: ParseException) {
        }

        return result
    }
}