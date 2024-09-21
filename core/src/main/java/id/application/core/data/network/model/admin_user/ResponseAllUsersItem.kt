package id.application.core.data.network.model.admin_user


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ResponseAllUsersItem(
    @SerializedName("data") val `data`: DataUsers,
    @SerializedName("message") val message: String?, // All users retrieved successfully.
    @SerializedName("success") val success: Boolean? // true
)



@Keep
data class DataUsers(
    @SerializedName("current_items_count") val currentItemsCount: Int?, // 10
    @SerializedName("current_page") val currentPage: Int?, // 2
    @SerializedName("items") val items: List<AllUsersItem>,
    @SerializedName("items_per_page") val itemsPerPage: Int?, // 10
    @SerializedName("total_users") val totalUsers: Int? // 24
)

@Keep
data class AllUsersItem(
    @SerializedName("email") val email: String?, // aurelie52@example.net
    @SerializedName("fullname") val fullname: String?, // Prof. Jalen Orn MD
    @SerializedName("id") val id: String?, // 9d0e5f06-2603-4a17-9a13-ecc594832b04
    @SerializedName("phone_number") val phoneNumber: String?, // 082729507717
    @SerializedName("roles") val roles: List<String?>?,
    @SerializedName("username") val username: String? // AMERTA.MISAEL49
)