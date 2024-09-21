package id.application.pointofsales.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.widget.Toast
import id.application.pointofsales.R
import id.application.pointofsales.databinding.DialogConfirmCustomBinding
import id.application.pointofsales.presentation.viewmodel.VmApplication
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

    fun showDialogLogout(context: Context, activity: Activity, layoutInflater: LayoutInflater, viewModel: VmApplication) {
        val binding: DialogConfirmCustomBinding = DialogConfirmCustomBinding.inflate(layoutInflater)
        val dialog = AlertDialog.Builder(context, 0).create()

        dialog.apply {
            setView(binding.root)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setCanceledOnTouchOutside(false)
        }.show()

        with(binding) {
            dialogTitle.text = "Anda Yakin Ingin Keluar\nAplikasi?"
            btnYes.setOnClickListener {
                viewModel.logout()
                activity.finishAffinity()
                dialog.dismiss()
            }
            btnNo.setOnClickListener {
                dialog.dismiss()
            }
            root.setOnTouchListener { _, _ -> true }
        }
    }
}