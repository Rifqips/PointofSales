package id.application.pointofsales.presentation.feature.setting

import id.application.pointofsales.databinding.FragmentSettingBinding
import id.application.pointofsales.presentation.viewmodel.VmApplication
import id.application.pointofsales.utils.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingFragment : BaseFragment<FragmentSettingBinding, VmApplication>(FragmentSettingBinding::inflate)  {

    override val viewModel: VmApplication by viewModel()

    override fun initView() {}

    override fun initListener() {}

}