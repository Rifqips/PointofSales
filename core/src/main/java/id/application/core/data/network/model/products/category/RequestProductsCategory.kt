package id.application.core.data.network.model.products.category

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class RequestProductsCategory(
    @SerializedName("name")
    val name: String
)