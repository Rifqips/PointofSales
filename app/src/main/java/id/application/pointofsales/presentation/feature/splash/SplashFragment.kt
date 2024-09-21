package id.application.pointofsales.presentation.feature.splash

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import id.application.pointofsales.R
import id.application.pointofsales.databinding.FragmentSplashBinding
import id.application.pointofsales.presentation.viewmodel.VmApplication
import id.application.pointofsales.utils.BaseFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : BaseFragment<FragmentSplashBinding, VmApplication>(FragmentSplashBinding::inflate)  {

    override val viewModel: VmApplication by viewModel()

    override fun initView() {}

    override fun initListener() {
        lifecycleScope.launch {
            delay(3000)
            checkLoginResult()
        }
    }

    private fun checkLoginResult() {
        viewModel.checkLogin()
        viewModel.isUserLogin.observe(viewLifecycleOwner) { isLogin ->
            if (!isLogin) {
                findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
            }else{
                findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
            }
        }
    }
}
