package id.application.core.data.network.model.auth

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ResponseLoginItem(
    @SerializedName("access_token") val accessToken: String?,
    @SerializedName("token_type") val tokenType: String?,
    @SerializedName("expires_in") val expiresIn: Int?
)
