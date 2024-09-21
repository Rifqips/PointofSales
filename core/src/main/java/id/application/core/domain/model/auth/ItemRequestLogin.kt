package id.application.core.data.network.model.auth


data class ItemRequestLogin(
    val password: String? = null,
    val username: String? = null
)

fun ItemRequestLogin.toRequestLoginItem(): RequestLoginItem {
    return RequestLoginItem(
        password = this.password,
        username = this.username
    )
}