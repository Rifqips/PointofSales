package id.application.core.data.network.model.profile

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ResponseProfileItem(
    @SerializedName("user") val user: UserProfileItem?,
    @SerializedName("roles") val roles: List<String>?
)


@Keep
data class UserProfileItem(
    @SerializedName("id") val id: String?,
    @SerializedName("email") val email: String?,
    @SerializedName("username") val username: String?,
    @SerializedName("fullname") val fullname: String?,
    @SerializedName("phone_number") val phoneNumber: String?,
    @SerializedName("created_at") val createdAt: String?,
    @SerializedName("updated_at") val updatedAt: String?
)
