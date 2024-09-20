package id.application.pointofsales.presentation.adapter.admin_users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.application.pointofsales.databinding.ItemAdminUsersBinding

class UserAdapter(
    private val userList: List<User>,
    private val onClick: (User) -> Unit
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    inner class UserViewHolder(private val binding: ItemAdminUsersBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            binding.tvUserIcon.text = user.icon
            binding.tvNameAccount.text = user.name
            binding.tvNickname.text = user.nickname
            binding.tvEmailAccount.text = user.phoneNumber
            binding.tvUserRole.text = user.role

            binding.tvDetail.setOnClickListener {
                onClick(user)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemAdminUsersBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int = userList.size
}


data class User(
    val name: String,
    val nickname: String,
    val phoneNumber: String,
    val role: String,
    val icon: String
)
