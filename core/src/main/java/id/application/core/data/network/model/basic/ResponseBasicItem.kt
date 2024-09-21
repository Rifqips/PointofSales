package id.application.core.data.network.model.basic


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ResponseBasicItem<T>(
    @SerializedName("success") val success: Boolean?,
    @SerializedName("message") val message: String?,
    @SerializedName("data") val data: T?
)