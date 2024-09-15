package id.application.pointofsales.presentation.feature.product

import id.application.pointofsales.databinding.FragmentProductBinding
import id.application.pointofsales.presentation.viewmodel.VmApplication
import id.application.pointofsales.utils.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductFragment : BaseFragment<FragmentProductBinding, VmApplication>(FragmentProductBinding::inflate) {

    override val viewModel: VmApplication by viewModel()

    override fun initView() {}

    override fun initListener() {}

}