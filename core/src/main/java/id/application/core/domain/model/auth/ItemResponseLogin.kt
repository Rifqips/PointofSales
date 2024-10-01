package id.application.core.data.network.model.auth

data class ItemResponseLogin(
    val accessToken: String?,
    val tokenType: String?,
    val expiresIn: String?
)

fun DataLoginItem.toItemResponseLogin(): ItemResponseLogin {
    return ItemResponseLogin(
        accessToken = this.accessToken,
        tokenType = this.tokenType,
        expiresIn = this.expiresIn
    )
}


