package id.application.pointofsales.presentation.feature.login

import androidx.core.view.isGone
import androidx.navigation.fragment.findNavController
import id.application.core.data.network.model.auth.ItemRequestLogin
import id.application.core.utils.proceedWhen
import id.application.pointofsales.R
import id.application.pointofsales.databinding.FragmentLoginBinding
import id.application.pointofsales.presentation.viewmodel.VmApplication
import id.application.pointofsales.utils.BaseFragment
import id.application.pointofsales.utils.Utils
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment :
    BaseFragment<FragmentLoginBinding, VmApplication>(FragmentLoginBinding::inflate) {

    override val viewModel: VmApplication by viewModel()

    override fun initView() {
        observeVm()
    }

    private var itemLogin = ItemRequestLogin()

    override fun initListener() {
        with(binding) {
            btnLogin.setOnClickListener {
                doLogin()
            }
        }
    }

    private fun observeVm(){
        viewModel.itemResponseLogin.observe(viewLifecycleOwner){
            it.proceedWhen(
                doOnSuccess = {
                    binding.pbLoading.isGone = true
                    it.message?.let { it1 -> Utils.showToast(it1, requireContext()) }
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                },
                doOnLoading = {
                    binding.pbLoading.isGone = false
                }
            )
        }
    }

    private fun doLogin() {
        itemLogin = ItemRequestLogin(
            username = binding.etEmailEditLogin.text.toString(),
            password = binding.etPasswordEditLogin.text.toString()
        )
        viewModel.login(itemLogin)
    }


}