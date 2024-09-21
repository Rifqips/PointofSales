package id.application.core.domain.model.admin_all_user


import id.application.core.data.network.model.admin_all_user.AllUsersItem
import id.application.core.data.network.model.admin_all_user.DataUsers
import id.application.core.data.network.model.admin_all_user.ResponseAllUsersItem

data class ItemResponseAllUsers(
    val `data`: UsersData?,
    val message: String?, // All users retrieved successfully.
    val success: Boolean? // true
)



data class UsersData(
    val currentItemsCount: Int?, // 10
    val currentPage: Int?, // 2
    val items: List<ItemAllUsers>,
    val itemsPerPage: Int?, // 10
    val totalUsers: Int? // 24
)

data class ItemAllUsers(
    val email: String?,
    val fullname: String?, // Prof. Jalen Orn MD
    val id: String?, // 9d0e5f06-2603-4a17-9a13-ecc594832b04
    val phoneNumber: String?, // 082729507717
    val roles: List<String?>?,
    val username: String? // AMERTA.MISAEL49
)

fun ResponseAllUsersItem.toItemResponseAllUsers(): ItemResponseAllUsers {
    return ItemResponseAllUsers(
        data = this.data?.toUsersData(),
        message = this.message,
        success = this.success
    )
}

fun DataUsers.toUsersData(): UsersData {
    return UsersData(
        currentItemsCount = this.currentItemsCount,
        currentPage = this.currentPage,
        items = this.items.map { it.toItemAllUsers() },
        itemsPerPage = this.itemsPerPage,
        totalUsers = this.totalUsers
    )
}

fun AllUsersItem.toItemAllUsers(): ItemAllUsers {
    return ItemAllUsers(
        email = this.email,
        fullname = this.fullname,
        id = this.id,
        phoneNumber = this.phoneNumber,
        roles = this.roles,
        username = this.username
    )
}
