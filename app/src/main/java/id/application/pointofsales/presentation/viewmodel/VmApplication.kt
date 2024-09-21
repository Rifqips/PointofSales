package id.application.pointofsales.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.liveData
import id.application.core.data.datasource.AppPreferenceDataSource
import id.application.core.data.network.model.auth.ItemRequestLogin
import id.application.core.data.network.model.auth.ItemResponseLogin
import id.application.core.domain.model.basic.ItemResponseBasic
import id.application.core.domain.model.profile.ItemResponseProfile
import id.application.core.domain.paging.UsersPagingSource
import id.application.core.domain.repository.ApplicationRepository
import id.application.core.utils.ResultWrapper
import id.application.pointofsales.presentation.adapter.admin_users.AdminUserPagingAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class VmApplication(
    private val preferencesDatasource: AppPreferenceDataSource,
    private val repository: ApplicationRepository
) : ViewModel() {

    private val _itemResponseLogin = MutableLiveData<ResultWrapper<ItemResponseBasic<ItemResponseLogin>>>()
    val itemResponseLogin: LiveData<ResultWrapper<ItemResponseBasic<ItemResponseLogin>>> = _itemResponseLogin

    private val _isUserLogin = MutableLiveData<Boolean>()
    val isUserLogin: LiveData<Boolean> = _isUserLogin

    private val _itemResponseProfile = MutableLiveData<ResultWrapper<ItemResponseBasic<ItemResponseProfile>>>()
    val itemResponseProfile: LiveData<ResultWrapper<ItemResponseBasic<ItemResponseProfile>>> = _itemResponseProfile

    private val _loadingPagingResults = MutableLiveData<Boolean>()
    val loadingPagingResults: LiveData<Boolean> = _loadingPagingResults

    init {
        getProfile()
    }


    fun login(request: ItemRequestLogin) {
        viewModelScope.launch {
            repository.login(request).collect { result ->
                _itemResponseLogin.postValue(result)
            }
        }
    }

    fun checkLogin() {
        viewModelScope.launch(Dispatchers.IO) {
            val userStatus = preferencesDatasource.getUserToken().firstOrNull()
            _isUserLogin.postValue(userStatus != null)
        }
    }

    fun getProfile(){
        viewModelScope.launch {
            repository.profile().collect{
                _itemResponseProfile.postValue(it)
            }
        }
    }

    fun deleteUserById(id: String? = null, ){
        viewModelScope.launch {
            repository.deleteUserById(id).collect{
                _itemResponseProfile.postValue(it)
            }
        }
    }


    fun loadPagingUsers(
        adapter: AdminUserPagingAdapter,
        pageItem: Int? = null
    ) {
        _loadingPagingResults.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getAllUsers(
                pageItem = pageItem,
            )
            if (response.success == true) {
                val postResponse = response
                postResponse.let {
                    val post = it.data?.items ?: emptyList()
                    adapter.submitData(PagingData.from(post))
                }
                _loadingPagingResults.postValue(false)
            }
        }
    }


    val userList = Pager(PagingConfig(pageSize = 10)) {
        UsersPagingSource(repository)
    }.liveData.cachedIn(viewModelScope)


    fun logout() {
        viewModelScope.launch {
            preferencesDatasource.deleteAllData()
        }
    }

}