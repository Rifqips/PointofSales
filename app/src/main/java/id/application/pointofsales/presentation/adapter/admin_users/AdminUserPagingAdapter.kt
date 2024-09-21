package id.application.pointofsales.presentation.adapter.admin_users

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import id.application.core.domain.model.admin_all_user.ItemAllUsers
import id.application.pointofsales.databinding.ItemAdminUsersBinding

class AdminUserPagingAdapter(
    private val onClickListener: (ItemAllUsers) -> Unit,
) : PagingDataAdapter<ItemAllUsers, AdminUserPagingAdapter.ItemViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ItemAllUsers>() {
            override fun areItemsTheSame(
                oldItem: ItemAllUsers,
                newItem: ItemAllUsers
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: ItemAllUsers,
                newItem: ItemAllUsers
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemAdminUsersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    inner class ItemViewHolder(
        private val binding: ItemAdminUsersBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n", "NewApi")
        fun bind(user: ItemAllUsers) {
            binding.tvNameAccount.text = user.fullname
            binding.tvNickname.text = user.username
            binding.tvEmailAccount.text = user.phoneNumber
            binding.tvUserRole.text = user.email

            binding.tvDetail.setOnClickListener {
                onClickListener(user)
            }
        }
    }
}
