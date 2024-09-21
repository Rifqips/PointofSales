package id.application.core.domain.model.profile

import id.application.core.data.network.model.profile.ResponseProfileItem
import id.application.core.data.network.model.profile.UserProfileItem

data class ItemResponseProfile(
    val user: ItemUserProfile?,
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

fun UserProfileItem.toUserProfileItem(): ItemUserProfile {
    return ItemUserProfile(
        id = this.id,
        email = this.email,
        username = this.username,
        fullname = this.fullname,
        phoneNumber = this.phoneNumber,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt
    )
}


fun ResponseProfileItem.toResponseProfileItem(): ItemResponseProfile {
    return ItemResponseProfile(
        user = this.user?.toUserProfileItem(),
        roles = this.roles
    )
}
