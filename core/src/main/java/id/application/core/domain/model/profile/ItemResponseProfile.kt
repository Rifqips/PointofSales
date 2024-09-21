package id.application.core.data.network.model.profile

data class ItemResponseProfile(
    val user: UserProfileItem?,
    val roles: List<String>?
)


data class ItemUserProfile(
    val id: String?,
    val email: String?,
    val username: String?,
    val fullname: String?,
    val phoneNumber: String?,
    val createdAt: String?,
    val updatedAt: String?
)

fun ItemUserProfile.toUserProfileItem(): UserProfileItem {
    return UserProfileItem(
        id = this.id,
        email = this.email,
        username = this.username,
        fullname = this.fullname,
        phoneNumber = this.phoneNumber,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt
    )
}

