package id.application.core.domain.repository

import androidx.lifecycle.LiveData
import id.application.core.data.local.database.cart.CartDao
import id.application.core.data.local.database.cart.CartKeys

interface ApplicationRoomRepository {

    suspend fun insertCart(carts: CartKeys)

    fun getCartList(): LiveData<List<CartKeys>>

    fun updateCart(id: String, quantity: Int, hargaJual: Int)

    fun getTotalHargaJual(): LiveData<Int>

    fun deleteProductById(id: String)


}

class ApplicationRoomRepositoryImpl(
    private val cartlistDao: CartDao,

    ): ApplicationRoomRepository {
    override suspend fun insertCart(carts: CartKeys) {
        return cartlistDao.insertCart(carts)
    }

    override fun getCartList(): LiveData<List<CartKeys>> {
        return cartlistDao.getCartList()
    }

    override fun updateCart(id: String, quantity: Int, hargaJual: Int) {
        return cartlistDao.updateCart(id, quantity, hargaJual)
    }

    override fun getTotalHargaJual(): LiveData<Int> {
        return cartlistDao.getTotalHargaJual()
    }

    override fun deleteProductById(id: String) {
        return cartlistDao.deleteProductById(id)
    }
}
