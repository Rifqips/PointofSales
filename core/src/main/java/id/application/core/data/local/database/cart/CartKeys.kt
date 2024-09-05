package id.application.core.data.local.database.cart

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_key")
data class CartKeys (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int? = null,
    @field:ColumnInfo(name = "url")
    val url: String,
    @field:ColumnInfo(name = "name")
    val name: String,
    @field:ColumnInfo(name = "bahan")
    val bahan: Int,
    @field:ColumnInfo(name = "jasa")
    val jasa: Int,
    @field:ColumnInfo(name = "laba")
    val laba: Int,
    @field:ColumnInfo(name = "hargaJual")
    val hargaJual: Int,
    @field:ColumnInfo("quantity")
    var quantity: Int = 1,
)
