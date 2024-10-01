package id.application.pointofsales.presentation.feature.login

import android.util.Log
import androidx.core.view.isGone
import androidx.navigation.fragment.findNavController
import id.application.core.data.network.model.auth.ItemRequestLogin
import id.application.core.utils.exceptions.ApiException
import id.application.core.utils.exceptions.NoInternetException
import id.application.core.utils.proceedWhen
import id.application.pointofsales.R
import id.application.pointofsales.databinding.FragmentLoginBinding
import id.application.pointofsales.presentation.viewmodel.VmApplication
import id.application.pointofsales.utils.BaseFragment
import id.application.pointofsales.utils.Utils
import id.application.pointofsales.utils.Utils.showToastFailed
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment :
    BaseFragment<FragmentLoginBinding, VmApplication>(FragmentLoginBinding::inflate) {

    override val viewModel: VmApplication by viewModel()


    override fun initView() {
        observeVm()
    }

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
                },
                doOnError = {
                    binding.pbLoading.isGone = false
                    binding.btnLogin.isClickable = true
                    when (val exception = it.exception) {
                        is ApiException -> {
                            when (exception.httpCode) {
                                400, 500 -> showToastFailed(exception.errorBody.toString(), requireContext())
                                else -> showToastFailed("API error: ${exception.httpCode}", requireContext())
                            }
                        }
                        is NoInternetException -> {
                            showToastFailed("Tidak ada jaringan internet", requireContext())
                        }
                        else -> {
                            showToastFailed("An unexpected error occurred", requireContext())
                        }
                    }
                }
            )
        }
    }

    private fun doLogin() {
        val itemLogin = ItemRequestLogin(
            username = binding.etEmailEditLogin.text.toString(),
            password = binding.etPasswordEditLogin.text.toString()
        )
        viewModel.login(itemLogin)
        Log.d("check-login", "from fragment $itemLogin")
    }
}