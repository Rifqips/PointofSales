package id.application.core.domain.repository

import androidx.lifecycle.LiveData
import id.application.core.data.local.database.cart.CartDao
import id.application.core.data.local.database.cart.CartKeys

interface ApplicationRoomRepository {

    suspend fun insertCart(carts: CartKeys)

    fun getCartList(): LiveData<List<CartKeys>>

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

    override fun deleteProductById(id: String) {
        return cartlistDao.deleteProductById(id)
    }
}
