package id.application.core.data.network.model.products.category

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ResponseProductCategory(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("updated_at")
    val updatedAt: String
)