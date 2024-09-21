package id.application.core.domain.model.basic

import id.application.core.data.network.model.basic.ResponseBasicItem


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
