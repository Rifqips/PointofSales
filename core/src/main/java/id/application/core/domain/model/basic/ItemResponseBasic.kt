package id.application.core.data.network.model.basic


data class ItemResponseBasic<T>(
    val success: Boolean?,
    val message: String?,
    val data: T?
)

fun <T> ResponseBasicItem<T>.toItemResponseBasic(): ItemResponseBasic<T> {
    return ItemResponseBasic(
        success = this.success,
        message = this.message,
        data = this.data
    )
}

fun <T> ItemResponseBasic<T>.toResponseBasicItem(): ResponseBasicItem<T> {
    return ResponseBasicItem(
        success = this.success,
        message = this.message,
        data = this.data
    )
}