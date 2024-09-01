package id.application.pointofsales.presentation.feature.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import id.application.pointofsales.R
import id.application.pointofsales.databinding.FragmentLoginBinding
import id.application.pointofsales.presentation.viewmodel.VmApplication
import id.application.pointofsales.utils.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment<FragmentLoginBinding, VmApplication>(FragmentLoginBinding::inflate) {

    override val viewModel: VmApplication by viewModel()

    override fun initView() {}

    override fun initListener() {
        with(binding) {
            btnLogin.setOnClickListener {
                Toast.makeText(requireContext(), "isClicked", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            }
        }
    }

}