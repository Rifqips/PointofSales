package id.application.pointofsales.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.application.core.data.local.database.cart.CartKeys
import id.application.core.domain.repository.ApplicationRoomRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VmApplication(
    private val roomRepository: ApplicationRoomRepository
) : ViewModel() {

    private val _cartList = MutableLiveData<List<CartKeys>>()
    val cartList : LiveData<List<CartKeys>> =_cartList

    fun insertCart(carts: CartKeys){
        viewModelScope.launch {
            roomRepository.insertCart(carts)
        }
    }

    fun getCartList(){
         viewModelScope.launch {
            roomRepository.getCartList().observeForever {
                _cartList.postValue(it)
            }
        }
    }

    fun deleteProductById(id: String){
        viewModelScope.launch(Dispatchers.IO){
            roomRepository.deleteProductById(id)
        }
    }


}