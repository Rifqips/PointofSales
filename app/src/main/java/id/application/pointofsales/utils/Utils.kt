package id.application.pointofsales.utils

import android.content.Context
import android.widget.Toast
import id.application.pointofsales.R
import io.github.muddz.styleabletoast.StyleableToast
import java.text.NumberFormat
import java.util.Locale

object Utils {

    fun formatRupiah(number: Int): String {
        val localeID = Locale("in", "ID")
        val numberFormat = NumberFormat.getCurrencyInstance(localeID)
        return numberFormat.format(number)
    }

    fun toast(message: String, context: Context) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

    }

    fun showToast(message: String, context: Context) {
        StyleableToast.makeText(
            context,
            message,
            R.style.successtoast
        ).show()
    }

    fun showToastFailed(message: String, context: Context) {
        StyleableToast.makeText(
            context,
            message,
            R.style.failedtoast
        ).show()
    }
}