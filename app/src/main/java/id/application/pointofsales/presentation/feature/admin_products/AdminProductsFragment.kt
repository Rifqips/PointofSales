package id.application.pointofsales.presentation.feature.admin_products

import id.application.pointofsales.databinding.FragmentAdminProductsBinding
import id.application.pointofsales.presentation.viewmodel.VmApplication
import id.application.pointofsales.utils.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class AdminProductsFragment : BaseFragment<FragmentAdminProductsBinding, VmApplication>(
    FragmentAdminProductsBinding::inflate) {

    override val viewModel: VmApplication by viewModel()

    override fun initView() {}

    override fun initListener() {}

}