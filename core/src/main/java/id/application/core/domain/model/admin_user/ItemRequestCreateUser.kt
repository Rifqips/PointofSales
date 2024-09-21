package id.application.core.domain.model.admin_user


import id.application.core.data.network.model.admin_user.RequestCreateUserItem

data class ItemRequestCreateUser(
    val email: String?,
    val fullname: String?,
    val phoneNumber: String?,
    val role: String?,
    val username: String?
)


fun ItemRequestCreateUser.toRequestCreateUser(): RequestCreateUserItem {
    return RequestCreateUserItem(
        email = this.email,
        fullname = this.fullname,
        phoneNumber = this.phoneNumber,
        role = this.role,
        username = this.username
    )
}