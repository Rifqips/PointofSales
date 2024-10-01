package id.application.core.data.network.model.products

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ResponseAllProductsItem(
    @SerializedName("data")
    val `data`: DataAllProductsItem,
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean
)


@Keep
data class ItemAllProducts(
    @SerializedName("category_name")
    val categoryName: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("material_cost")
    val materialCost: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("profit")
    val profit: String,
    @SerializedName("service_cost")
    val serviceCost: String,
    @SerializedName("stock")
    val stock: String
)


@Keep
data class DataAllProductsItem(
    @SerializedName("current_items_count")
    val currentItemsCount: Int,
    @SerializedName("current_page")
    val currentPage: Int,
    @SerializedName("items")
    val items: List<ItemAllProducts>,
    @SerializedName("items_per_page")
    val itemsPerPage: Int,
    @SerializedName("total_items")
    val totalItems: Int
)