package id.application.pointofsales.presentation.feature.home

import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import id.application.pointofsales.R
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

        if (navController != null) {
            binding.navigationRail.setupWithNavController(navController)
            binding.navigationRail.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.menuCashier -> {
                        navController.navigate(R.id.cashierFragment)
                    }
                    R.id.menuProduct -> {
                        navController.navigate(R.id.productFragment)
                    }
                    R.id.menuHistory -> {
                        navController.navigate(R.id.historyFragment)
                    }
                    R.id.menuUsers -> {
                        navController.navigate(R.id.adminUsersFragment)
                    }
                    R.id.menuAccount -> {
                        navController.navigate(R.id.accountFragment)
                    }
                }
                true
            }
        }
    }
}
