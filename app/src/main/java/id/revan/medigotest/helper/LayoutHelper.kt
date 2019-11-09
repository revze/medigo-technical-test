package id.revan.medigotest.helper

import id.revan.medigotest.R
import javax.inject.Inject

class LayoutHelper @Inject constructor() {
    fun getWeatherImage(iconName: String): Int {
        return if (iconName.startsWith("01")) {
            R.drawable.ic_sun
        } else if (iconName.startsWith("11")) {
            R.drawable.ic_cloudlightning
        } else if (iconName.startsWith("09")) {
            R.drawable.ic_cloudrain
        } else if (iconName.startsWith("10")) {
            R.drawable.ic_clouddrizzle
        } else {
            R.drawable.ic_cloudcloudy
        }
    }
}