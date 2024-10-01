package id.application.pointofsales.presentation.viewmodel

import android.util.Log
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
import id.application.core.data.network.model.auth.ResponseLoginItem
import id.application.core.data.network.model.products.DataCreateProducts
import id.application.core.data.network.model.products.ItemAllProducts
import id.application.core.data.network.model.products.RequestCreateProductsItem
import id.application.core.data.network.model.products.category.RequestProductsCategory
import id.application.core.data.network.model.products.category.ResponseProductCategory
import id.application.core.data.network.model.profile.UserProfileItem
import id.application.core.domain.model.admin_user.ItemRequestCreateUser
import id.application.core.domain.model.basic.ItemResponseBasic
import id.application.core.domain.model.profile.ItemResponseProfile
import id.application.core.domain.paging.CategoriesPagingSource
import id.application.core.domain.paging.ProductsPagingSource
import id.application.core.domain.paging.UsersPagingSource
import id.application.core.domain.repository.ApplicationRepository
import id.application.core.utils.ResultWrapper
import id.application.pointofsales.presentation.adapter.admin_users.AdminUserPagingAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VmApplication(
    private val preferencesDatasource: AppPreferenceDataSource,
    private val repository: ApplicationRepository
) : ViewModel() {

    private val _itemResponseLogin = MutableLiveData<ResultWrapper<ResponseLoginItem>>()
    val itemResponseLogin: LiveData<ResultWrapper<ResponseLoginItem>> = _itemResponseLogin

    private val _isUserLogin = MutableLiveData<Boolean>()
    val isUserLogin: LiveData<Boolean> = _isUserLogin

    private val _itemResponseProfile = MutableLiveData<ResultWrapper<ItemResponseBasic<ItemResponseProfile>>>()
    val itemResponseProfile: LiveData<ResultWrapper<ItemResponseBasic<ItemResponseProfile>>> = _itemResponseProfile

    private val _itemResponseCreateUser = MutableLiveData<ResultWrapper<ItemResponseBasic<UserProfileItem>>>()
    val itemResponseCreateUser: LiveData<ResultWrapper<ItemResponseBasic<UserProfileItem>>> = _itemResponseCreateUser

    private val _itemResponseCreateProducts = MutableLiveData<ResultWrapper<ItemResponseBasic<DataCreateProducts>>>()
    val itemResponseCreateProducts: LiveData<ResultWrapper<ItemResponseBasic<DataCreateProducts>>> = _itemResponseCreateProducts

    private val _itemResponseProductId = MutableLiveData<ResultWrapper<ItemResponseBasic<ItemAllProducts>>>()
    val itemResponseProductId: LiveData<ResultWrapper<ItemResponseBasic<ItemAllProducts>>> = _itemResponseProductId

    private val _itemResponseProductCategory = MutableLiveData<ResultWrapper<ItemResponseBasic<ResponseProductCategory>>>()
    val itemResponseProductCategory: LiveData<ResultWrapper<ItemResponseBasic<ResponseProductCategory>>> = _itemResponseProductCategory

    private val _loadingPagingResults = MutableLiveData<Boolean>()
    val loadingPagingResults: LiveData<Boolean> = _loadingPagingResults


    fun login(request: ItemRequestLogin) {
        viewModelScope.launch {
            repository.login(request).collect { result ->
                _itemResponseLogin.postValue(result)
            }
        }
        Log.d("check-login", "from vm $request")
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

    fun createUser(request: ItemRequestCreateUser) {
        viewModelScope.launch {
            repository.createUser(request).collect{
                _itemResponseCreateUser.postValue(it)
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

    fun createProducts(request: RequestCreateProductsItem){
        viewModelScope.launch {
            repository.createProducts(request).collect{
                _itemResponseCreateProducts.postValue(it)
            }
        }
    }

    fun updateProducts(request: RequestCreateProductsItem){
        viewModelScope.launch {
            repository.createProducts(request).collect{
                _itemResponseCreateProducts.postValue(it)
            }
        }
    }

//    suspend fun getAllProducts(
//        pageItem: Int? = null,
//        limit: Int? = null,
//        search: String? = null,
//    ): ResponseAllProductsItem


    val productsList = Pager(PagingConfig(pageSize = 10)) {
        ProductsPagingSource(repository)
    }.liveData.cachedIn(viewModelScope)

    fun getProductId(id: String? = null){
        viewModelScope.launch {
            repository.getProductId(id).collect{
                _itemResponseProductId.postValue(it)
            }
        }
    }

    fun deleteProduct(id: String? = null){
        viewModelScope.launch {
            repository.getProductId(id).collect{
                _itemResponseProductId.postValue(it)
            }
        }
    }

    fun createProductCategory(request: RequestProductsCategory){
        viewModelScope.launch {
            repository.createProductCategory(request).collect{
                _itemResponseProductCategory.postValue(it)
            }
        }
    }

    fun getAllProductCategories(
        pageItem: Int? = null,
        limit: Int? = null,
        search: String? = null,
    ){}

    val categoriesList = Pager(PagingConfig(pageSize = 10)) {
        CategoriesPagingSource(repository)
    }.liveData.cachedIn(viewModelScope)

    suspend fun getProductCategoryById(id: String? = null){
        viewModelScope.launch {
            repository.getProductCategoryById()
        }
    }

    suspend fun updateProductCategory(id: String? = null,request: RequestProductsCategory,){
        viewModelScope.launch {
            repository.updateProductCategory(id, request).collect{
                _itemResponseProductCategory.postValue(it)
            }
        }
    }

    suspend fun deleteProductCategory(id: String? = null,){
        viewModelScope.launch {
            repository.deleteProductCategory(id).collect{
                _itemResponseProductCategory.postValue(it)
            }
        }
    }
}