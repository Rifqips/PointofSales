package id.application.core.data.network.model.auth


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class RequestLoginItem(
    @SerializedName("password") val password: String?,
    @SerializedName("username") val username: String?
)