package id.application.pointofsales.presentation.feature.account

import id.application.core.domain.model.profile.ItemResponseProfile
import id.application.core.utils.proceedWhen
import id.application.pointofsales.databinding.FragmentAccountBinding
import id.application.pointofsales.presentation.viewmodel.VmApplication
import id.application.pointofsales.utils.BaseFragment
import id.application.pointofsales.utils.Utils.showDialogLogout
import org.koin.androidx.viewmodel.ext.android.viewModel

class AccountFragment :
    BaseFragment<FragmentAccountBinding, VmApplication>(FragmentAccountBinding::inflate)  {

    override val viewModel: VmApplication by viewModel()

    override fun initView() {
        observeVm()
    }

    override fun initListener() {
        with(binding){
            btnLogout.setOnClickListener {
                showDialogLogout(requireContext(), requireActivity(), layoutInflater, viewModel)
            }
        }
    }

    private fun observeVm(){
        with(viewModel){
            itemResponseProfile.observe(viewLifecycleOwner){
                it.proceedWhen(
                    doOnSuccess = {
                        it.payload?.data?.let { profile -> setProfile(profile) }
                    },
                    doOnError = {}
                )
            }
        }
    }

    private fun setProfile(data: ItemResponseProfile){
        with(binding){
            tvUserIcon.text =  data.user?.fullname?.get(0).toString()
            tvNameAccount.text = data.user?.fullname
            tvNickname.text = data.user?.username
            tvEmailAccount.text = data.user?.email
            tvUserRole.text = data.roles?.first()
        }
    }

}