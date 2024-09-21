package id.application.pointofsales.presentation.feature.admin_users

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.core.view.isGone
import androidx.recyclerview.widget.GridLayoutManager
import id.application.core.domain.model.admin_all_user.ItemAllUsers
import id.application.core.utils.proceedWhen
import id.application.pointofsales.databinding.DialogAdminCreateUserBinding
import id.application.pointofsales.databinding.DialogAdminDetailUserBinding
import id.application.pointofsales.databinding.FragmentAdminUsersBinding
import id.application.pointofsales.presentation.adapter.admin_users.AdminUserPagingAdapter
import id.application.pointofsales.presentation.viewmodel.VmApplication
import id.application.pointofsales.utils.BaseFragment
import id.application.pointofsales.utils.Utils
import org.koin.androidx.viewmodel.ext.android.viewModel

class AdminUsersFragment : BaseFragment<FragmentAdminUsersBinding, VmApplication>(FragmentAdminUsersBinding::inflate) {
    override val viewModel: VmApplication by viewModel()

    private var activeDialog: AlertDialog? = null

    private val adapterUsers: AdminUserPagingAdapter by lazy {
        AdminUserPagingAdapter {
            showDialogDetailUser(it)
        }
    }

    override fun initView() {
        observeVm()
        loadPagingUsers(adapterUsers)
    }

    private fun loadPagingUsers(adapter: AdminUserPagingAdapter) {
        viewModel.loadPagingUsers(adapter)
    }

    private fun observeVm() {
        with(viewModel) {
            // Observe paging data
            userList.observe(viewLifecycleOwner) { pagingData ->
                adapterUsers.submitData(lifecycle, pagingData)
            }

            // Observe loading state
            loadingPagingResults.observe(viewLifecycleOwner) { loading ->
                binding.pbUsers.isGone = !loading
            }

            // Observe profile response
            itemResponseProfile.observe(viewLifecycleOwner) {
                it.proceedWhen(
                    doOnLoading = {
                        binding.pbUsers.isGone = false
                    },
                    doOnSuccess = { response ->
                        binding.pbUsers.isGone = true
                        response.message?.let { message ->
                            Utils.showToast(message, requireContext())
                        }

                        // Tutup dialog jika sukses
                        activeDialog?.dismiss()
                        activeDialog = null

                        // Reload Paging Data secara eksplisit
                        viewModel.loadPagingUsers(adapterUsers)
                    },
                    doOnError = {
                        binding.pbUsers.isGone = true
                        Utils.showToast("Error occurred", requireContext())
                    }
                )
            }
        }

        // Setup RecyclerView
        binding.rvListUsers.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = adapterUsers
        }
    }

    override fun initListener() {
        binding.btnCreateUser.setOnClickListener {
            showDialogAddUsers()
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun showDialogAddUsers() {
        activeDialog?.let { dialog ->
            if (dialog.isShowing) {
                dialog.dismiss()
            }
        }

        val binding: DialogAdminCreateUserBinding = DialogAdminCreateUserBinding.inflate(layoutInflater)
        val dialog = AlertDialog.Builder(requireContext(), 0).create()
        dialog.apply {
            setView(binding.root)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setCanceledOnTouchOutside(false)
        }.show()
        activeDialog = dialog

        with(binding) {
            ivClose.setOnClickListener {
                dialog.dismiss()
                activeDialog = null
            }
        }
        binding.root.setOnTouchListener { _, _ -> true }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun showDialogDetailUser(item: ItemAllUsers) {
        activeDialog?.let { dialog ->
            if (dialog.isShowing) {
                dialog.dismiss()
            }
        }

        val binding: DialogAdminDetailUserBinding = DialogAdminDetailUserBinding.inflate(layoutInflater)
        val dialog = AlertDialog.Builder(requireContext(), 0).create()
        dialog.apply {
            setView(binding.root)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setCanceledOnTouchOutside(false)
        }.show()
        activeDialog = dialog

        with(binding) {
            tvUserIcon.text = item.fullname?.get(0).toString()
            etFullnameEdit.setText(item.fullname)
            etUsernameEdit.setText(item.username)
            etEmailEdit.setText(item.email)
            etPhoneNumberEdit.setText(item.phoneNumber)
            etUserRoleEdit.setText(item.roles?.first())

            btnDelete.setOnClickListener {
                viewModel.deleteUserById(item.id)
            }

            ivClose.setOnClickListener {
                dialog.dismiss()
                activeDialog = null
            }
        }

        binding.root.setOnTouchListener { _, _ -> true }
    }
}

