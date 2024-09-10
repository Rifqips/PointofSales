package id.application.pointofsales.presentation.feature.home

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import androidx.window.layout.WindowMetricsCalculator
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.android.material.navigationrail.NavigationRailView
import id.application.pointofsales.R
import id.application.pointofsales.databinding.DialogPrintBillsBinding
import id.application.pointofsales.databinding.FragmentHomeBinding
import id.application.pointofsales.presentation.viewmodel.VmApplication
import id.application.pointofsales.utils.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment :
    BaseFragment<FragmentHomeBinding, VmApplication>(FragmentHomeBinding::inflate) {

    override val viewModel: VmApplication by viewModel()

    override fun initView() {
        setUpFragment()
    }

    override fun initListener() {}

    private fun setUpFragment() {
        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.container_bottom_navigation) as? NavHostFragment
        val navController = navHostFragment?.navController
        val metrics = context?.let {
            WindowMetricsCalculator.getOrCreate().computeCurrentWindowMetrics(it)
        }
        val withDp = metrics?.bounds?.width()?.div(resources.displayMetrics.density)

        with(binding) {
            if (withDp != null) {
                when {
                    withDp < 600f -> {
                        if (binding.bottomNavigation is BottomNavigationView && navController != null) {
                            val bottomNav = binding.bottomNavigation as BottomNavigationView
                            bottomNav.setupWithNavController(navController)
                            bottomNav.setOnItemSelectedListener {
                                when (it.itemId) {
                                    R.id.menuCashier -> {
                                        navController.navigate(R.id.cashierFragmentFragment)
                                    }
                                    R.id.menuProduct -> {
                                        navController.navigate(R.id.productFragment)
                                    }
                                    R.id.menuSetting -> {
                                        navController.navigate(R.id.settingFragment)
                                    }
                                }
                                true
                            }
                        }
                    }
                    withDp < 840f -> {
                        if (binding.bottomNavigation is NavigationRailView && navController != null) {
                            val navRail = binding.bottomNavigation as NavigationRailView
                            navRail.setupWithNavController(navController)
                            navRail.setOnItemSelectedListener {
                                when (it.itemId) {
                                    R.id.menuCashier -> {
                                        navController.navigate(R.id.cashierFragmentFragment)
                                    }
                                    R.id.menuProduct -> {
                                        navController.navigate(R.id.productFragment)
                                    }
                                    R.id.menuSetting -> {
                                        navController.navigate(R.id.settingFragment)
                                    }
                                }
                                true
                            }
                        }
                    }
                    else -> {
                        if (binding.bottomNavigation is NavigationView && navController != null) {
                            val bottomNav = binding.bottomNavigation as BottomNavigationView
                            bottomNav.setupWithNavController(navController)
                            bottomNav.setOnItemSelectedListener {
                                when (it.itemId) {
                                    R.id.menuCashier -> {
                                        navController.navigate(R.id.cashierFragmentFragment)
                                    }
                                    R.id.menuProduct -> {
                                        navController.navigate(R.id.productFragment)
                                    }
                                    R.id.menuSetting -> {
                                        navController.navigate(R.id.settingFragment)
                                    }
                                }
                                true
                            }
                        }
                    }
                }
            }
        }
    }


    private var activeDialog: AlertDialog? = null

    @SuppressLint("ClickableViewAccessibility")
    private fun showDialogConfirmSaveData() {
        activeDialog?.let { dialog ->
            if (dialog.isShowing) {
                dialog.dismiss()
            }
        }
        val binding: DialogPrintBillsBinding = DialogPrintBillsBinding.inflate(layoutInflater)
        val dialog = AlertDialog.Builder(requireContext(), 0).create()
        dialog.apply {
            setView(binding.root)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setCanceledOnTouchOutside(false)
        }.show()
        activeDialog = dialog
        with(binding) {
            ivClose.setOnClickListener {
                dialog.dismiss()
                activeDialog = null
            }
        }
        binding.root.setOnTouchListener { _, _ ->
            true
        }
    }

}
