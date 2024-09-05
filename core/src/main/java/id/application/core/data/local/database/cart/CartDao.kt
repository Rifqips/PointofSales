package id.application.core.data.local.database.cart

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCart(carts: CartKeys)

    @Query("SELECT * FROM cart_key")
    fun getCartList(): LiveData<List<CartKeys>>

    @Query("DELETE FROM cart_key WHERE id = :id")
    fun deleteProductById(id: String)

    @Query("DELETE FROM cart_key")
    fun deleteAllCart()
}