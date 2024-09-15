package id.application.pointofsales.presentation.feature.account

import id.application.pointofsales.databinding.FragmentAccountBinding
import id.application.pointofsales.presentation.viewmodel.VmApplication
import id.application.pointofsales.utils.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class AccountFragment :
    BaseFragment<FragmentAccountBinding, VmApplication>(FragmentAccountBinding::inflate)  {

    override val viewModel: VmApplication by viewModel()

    override fun initView() {}

    override fun initListener() {}

}