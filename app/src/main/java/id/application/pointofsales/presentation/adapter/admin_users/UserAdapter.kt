package id.application.pointofsales.presentation.adapter.admin_users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.application.pointofsales.databinding.ItemListUsersBinding

class UserAdapter(
    private val userList: List<User>,
    private val onClick: (User) -> Unit
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    // ViewHolder class with ViewBinding
    inner class UserViewHolder(private val binding: ItemListUsersBinding) :
        RecyclerView.ViewHolder(binding.root) {

        // Function to bind data to the views
        fun bind(user: User) {
            binding.tvUserIcon.text = user.icon
            binding.tvNameAccount.text = user.name
            binding.tvNickname.text = user.nickname
            binding.tvEmailAccount.text = user.phoneNumber
            binding.tvUserRole.text = user.role

            // Handle click event
            binding.tvDetail.setOnClickListener {
                onClick(user)
            }
        }
    }

    // Inflating the layout using ViewBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemListUsersBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UserViewHolder(binding)
    }

    // Binding data to ViewHolder
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]
        holder.bind(user)
    }

    // Returning the size of the list
    override fun getItemCount(): Int = userList.size
}


data class User(
    val name: String,
    val nickname: String,
    val phoneNumber: String,
    val role: String,
    val icon: String // Assuming this is the initial of the name (e.g., "R")
)
