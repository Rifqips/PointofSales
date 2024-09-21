package id.application.core.data.network.model.auth

data class ItemResponseLogin(
    val accessToken: String?,
    val tokenType: String?,
    val expiresIn: Int?
)

// Mapper function to convert ResponseLoginItem to ItemResponseLogin
fun ResponseLoginItem.toItemResponseLogin(): ItemResponseLogin {
    return ItemResponseLogin(
        accessToken = this.accessToken,
        tokenType = this.tokenType,
        expiresIn = this.expiresIn
    )
}

// Mapper function to convert ItemResponseLogin to ResponseLoginItem
fun ItemResponseLogin.toResponseLoginItem(): ResponseLoginItem {
    return ResponseLoginItem(
        accessToken = this.accessToken,
        tokenType = this.tokenType,
        expiresIn = this.expiresIn
    )
}

