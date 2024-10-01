package id.application.core.data.network.model.products


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class DataCreateProducts(
    @SerializedName("created_at")
    val createdAt: String,
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
    @SerializedName("product_category_id")
    val productCategoryId: String,
    @SerializedName("profit")
    val profit: String,
    @SerializedName("service_cost")
    val serviceCost: String,
    @SerializedName("stock")
    val stock: Int,
    @SerializedName("updated_at")
    val updatedAt: String
)