package id.application.pointofsales.presentation.feature.cashier

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.application.pointofsales.R
import id.application.pointofsales.databinding.FragmentCashierFragmentBinding
import id.application.pointofsales.presentation.viewmodel.VmApplication
import id.application.pointofsales.utils.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class CashierFragmentFragment :
    BaseFragment<FragmentCashierFragmentBinding, VmApplication>(FragmentCashierFragmentBinding::inflate) {

    override val viewModel: VmApplication by viewModel()

    override fun initView() {}

    override fun initListener() {}

}