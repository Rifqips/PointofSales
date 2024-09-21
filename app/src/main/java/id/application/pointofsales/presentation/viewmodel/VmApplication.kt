package id.application.pointofsales.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.application.core.data.network.model.auth.ItemRequestLogin
import id.application.core.data.network.model.auth.ItemResponseLogin
import id.application.core.data.network.model.basic.ItemResponseBasic
import id.application.core.domain.repository.ApplicationRepository
import id.application.core.utils.ResultWrapper
import kotlinx.coroutines.launch

class VmApplication(
    private val repository: ApplicationRepository
) : ViewModel() {

    private val _itemResponseLogin = MutableLiveData<ResultWrapper<ItemResponseBasic<ItemResponseLogin>>>()
    val itemResponseLogin: LiveData<ResultWrapper<ItemResponseBasic<ItemResponseLogin>>> = _itemResponseLogin

    fun login(request: ItemRequestLogin) {
        viewModelScope.launch {
            repository.login(request).collect { result ->
                _itemResponseLogin.postValue(result)
            }
        }
    }

}