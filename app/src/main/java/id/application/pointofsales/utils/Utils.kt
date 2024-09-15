package id.application.pointofsales.utils

import java.text.NumberFormat
import java.util.Locale

object Utils {

    fun formatRupiah(number: Int): String {
        val localeID = Locale("in", "ID")
        val numberFormat = NumberFormat.getCurrencyInstance(localeID)
        return numberFormat.format(number)
    }
}