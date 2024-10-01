package id.application.core.data.network.model.auth


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ResponseLoginItem(
    @SerializedName("data")
    val `data`: DataLoginItem,
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean
)

@Keep
data class DataLoginItem(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("expires_in")
    val expiresIn: String,
    @SerializedName("token_type")
    val tokenType: String
)