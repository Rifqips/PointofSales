package id.application.pointofsales.presentation.feature.product

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.application.pointofsales.R
import id.application.pointofsales.databinding.FragmentProductBinding
import id.application.pointofsales.presentation.viewmodel.VmApplication
import id.application.pointofsales.utils.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductFragment : BaseFragment<FragmentProductBinding, VmApplication>(FragmentProductBinding::inflate) {

    override val viewModel: VmApplication by viewModel()

    override fun initView() {}

    override fun initListener() {}

}