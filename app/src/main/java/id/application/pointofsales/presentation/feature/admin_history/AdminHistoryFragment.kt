package id.application.pointofsales.presentation.feature.admin_history

import androidx.fragment.app.viewModels
import id.application.pointofsales.databinding.FragmentAdminHistoryBinding
import id.application.pointofsales.presentation.viewmodel.VmApplication
import id.application.pointofsales.utils.BaseFragment


class AdminHistoryFragment :
    BaseFragment<FragmentAdminHistoryBinding, VmApplication>(FragmentAdminHistoryBinding::inflate){

    override val viewModel: VmApplication by viewModels()

    override fun initView() {}

    override fun initListener() {}

}