package id.application.core.data.network.model.products.category

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ResponseAllProductCategory(
    @SerializedName("data")
    val `data`: DataAllProductCategory,
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean
)

@Keep
data class ItemAllProductCategory(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)

@Keep
data class DataAllProductCategory(
    @SerializedName("current_items_count")
    val currentItemsCount: Int,
    @SerializedName("current_page")
    val currentPage: Int,
    @SerializedName("items")
    val items: List<ItemAllProductCategory>,
    @SerializedName("items_per_page")
    val itemsPerPage: Int,
    @SerializedName("total_items")
    val totalItems: Int
)