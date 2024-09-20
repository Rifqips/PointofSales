package id.application.pointofsales.presentation.feature.admin_users

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import id.application.pointofsales.databinding.DialogAdminCreateUserBinding
import id.application.pointofsales.databinding.FragmentAdminUsersBinding
import id.application.pointofsales.presentation.adapter.admin_users.User
import id.application.pointofsales.presentation.adapter.admin_users.UserAdapter
import id.application.pointofsales.presentation.viewmodel.VmApplication
import id.application.pointofsales.utils.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class AdminUsersFragment :
    BaseFragment<FragmentAdminUsersBinding, VmApplication>(FragmentAdminUsersBinding::inflate) {
    override val viewModel: VmApplication by viewModel()

    private lateinit var userAdapter: UserAdapter

    private val sampleUsers = listOf(
        User("Rifqi Padi Siliwangi", "AMERTA.PADI", "085123456780", "Staff - Cashier", "R"),
        User("Aulia Rahman", "AMERTA.AULIA", "085678123456", "Staff - Admin", "A"),
        User("Joko Purwanto", "AMERTA.PURWANTO", "085678123456", "Staff - Admin", "J"),
        User("Tejo Sutejo", "AMERTA.TEJO", "085678123456", "Staff - Admin", "T"),
        User("Puspitasari F", "AMERTA.PUSPITA", "085678123456", "Staff - Admin", "P"),
    )

    override fun initView() {
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        userAdapter = UserAdapter(sampleUsers) { user ->
            showUserDetails(user)
        }

        binding.rvListUsers.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = userAdapter
        }
    }

    private fun showUserDetails(user: User) {
        Toast.makeText(requireContext(), "Clicked on: ${user.name}", Toast.LENGTH_SHORT).show()
    }

    override fun initListener() {
        with(binding) {
            btnCreateUser.setOnClickListener {
                showDialogAddUsers()
            }
        }
    }

    private var activeDialog: AlertDialog? = null

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
        binding.root.setOnTouchListener { _, _ ->
            true
        }
    }
}
