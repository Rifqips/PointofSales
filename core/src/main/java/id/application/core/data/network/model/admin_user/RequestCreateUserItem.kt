package id.application.core.data.network.model.admin_user


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class RequestCreateUserItem(
    @SerializedName("email") val email: String?,
    @SerializedName("fullname") val fullname: String?,
    @SerializedName("phone_number") val phoneNumber: String?,
    @SerializedName("role") val role: String?,
    @SerializedName("username") val username: String?
)