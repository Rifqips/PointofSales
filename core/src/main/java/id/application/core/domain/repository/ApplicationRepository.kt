package id.application.core.domain.repository

import android.util.Log
import id.application.core.data.datasource.AppPreferenceDataSource
import id.application.core.data.datasource.ApplicationDataSource
import id.application.core.data.network.model.auth.ItemRequestLogin
import id.application.core.data.network.model.auth.ItemResponseLogin
import id.application.core.data.network.model.auth.toItemResponseLogin
import id.application.core.data.network.model.auth.toRequestLoginItem
import id.application.core.data.network.model.products.DataCreateProducts
import id.application.core.data.network.model.products.ItemAllProducts
import id.application.core.data.network.model.products.RequestCreateProductsItem
import id.application.core.data.network.model.products.ResponseAllProductsItem
import id.application.core.data.network.model.products.category.RequestProductsCategory
import id.application.core.data.network.model.products.category.ResponseAllProductCategory
import id.application.core.data.network.model.products.category.ResponseProductCategory
import id.application.core.data.network.model.profile.UserProfileItem
import id.application.core.domain.model.admin_user.ItemRequestCreateUser
import id.application.core.domain.model.admin_user.ItemResponseAllUsers
import id.application.core.domain.model.admin_user.toItemResponseAllUsers
import id.application.core.domain.model.admin_user.toRequestCreateUser
import id.application.core.domain.model.basic.ItemResponseBasic
import id.application.core.domain.model.basic.toItemResponseBasic
import id.application.core.domain.model.profile.ItemResponseProfile
import id.application.core.domain.model.profile.toResponseProfileItem
import id.application.core.utils.ResultWrapper
import id.application.core.utils.proceedFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.runBlocking

interface ApplicationRepository {

    suspend fun login(
        request: ItemRequestLogin
    ): Flow<ResultWrapper<ItemResponseBasic<ItemResponseLogin>>>

    suspend fun profile(): Flow<ResultWrapper<ItemResponseBasic<ItemResponseProfile>>>

    suspend fun getAllUsers(
        pageItem: Int? = null,
    ): ItemResponseAllUsers

    suspend fun deleteUserById(
        id: String? = null,
    ): Flow<ResultWrapper<ItemResponseBasic<ItemResponseProfile>>>

    suspend fun createUser(
        request: ItemRequestCreateUser,
    ): Flow<ResultWrapper<ItemResponseBasic<UserProfileItem>>>

    suspend fun createProducts(
        request: RequestCreateProductsItem,
    ): Flow<ResultWrapper<ItemResponseBasic<DataCreateProducts>>>

    suspend fun updateProducts(
        id: String? = null,
        request: RequestCreateProductsItem,
    ): Flow<ResultWrapper<ItemResponseBasic<DataCreateProducts>>>

    suspend fun getAllProducts(
        pageItem: Int? = null,
        limit: Int? = null,
        search: String? = null,
    ): ResponseAllProductsItem

    suspend fun getProductId(
        id: String? = null,
    ): Flow<ResultWrapper<ItemResponseBasic<ItemAllProducts>>>

    suspend fun deleteProduct(
        id: String? = null,
    ): Flow<ResultWrapper<ItemResponseBasic<ItemAllProducts>>>

    suspend fun createProductCategory(
        request: RequestProductsCategory,
    ): Flow<ResultWrapper<ItemResponseBasic<ResponseProductCategory>>>

    suspend fun getAllProductCategories(
        pageItem: Int? = null,
        limit: Int? = null,
        search: String? = null,
    ): ResponseAllProductCategory

    suspend fun getProductCategoryById(
        id: String? = null,
    ):  Flow<ResultWrapper<ItemResponseBasic<ResponseProductCategory>>>

    suspend fun updateProductCategory(
        id: String? = null,
        request: RequestProductsCategory,
    ): Flow<ResultWrapper<ItemResponseBasic<ResponseProductCategory>>>

    suspend fun deleteProductCategory(
        id: String? = null,
    ): Flow<ResultWrapper<ItemResponseBasic<ResponseProductCategory>>>

}

class ApplicationRepositoryImpl(
    private val source: ApplicationDataSource,
    private val pref: AppPreferenceDataSource
) : ApplicationRepository {

    override suspend fun login(request: ItemRequestLogin): Flow<ResultWrapper<ItemResponseBasic<ItemResponseLogin>>> {
        return proceedFlow {
            val response = source.login(request.toRequestLoginItem()).toItemResponseBasic()
            val mappedData = response.data?.toItemResponseLogin()
            mappedData?.accessToken?.let {
                pref.saveUserToken(it)
                Log.d("check-login", request.toString())
            }
            ItemResponseBasic(
                success = response.success,
                message = response.message,
                data = mappedData
            )

        }.catch {
            emit(ResultWrapper.Error(Exception(it)))
        }.onStart {
            emit(ResultWrapper.Loading())
        }
    }

    override suspend fun profile(): Flow<ResultWrapper<ItemResponseBasic<ItemResponseProfile>>> {
        return proceedFlow {
            val response = source.profile().toItemResponseBasic()
            ItemResponseBasic(
                success = response.success,
                message = response.message,
                data = response.data?.toResponseProfileItem()
            )
        }.catch {
            emit(ResultWrapper.Error(Exception(it)))
        }.onStart {
            emit(ResultWrapper.Loading())
        }
    }

    override suspend fun getAllUsers(
        pageItem: Int?,
    ): ItemResponseAllUsers {
        return source.getAllUsers(pageItem).toItemResponseAllUsers()
    }

    override suspend fun deleteUserById(id: String?): Flow<ResultWrapper<ItemResponseBasic<ItemResponseProfile>>> {
        return proceedFlow {
            val response = source.deleteUserById(id).toItemResponseBasic()
            ItemResponseBasic(
                success = response.success,
                message = response.message,
                data = response.data?.toResponseProfileItem()
            )
        }.catch {
            emit(ResultWrapper.Error(Exception(it)))
        }.onStart {
            emit(ResultWrapper.Loading())
        }
    }

    override suspend fun createUser(request: ItemRequestCreateUser): Flow<ResultWrapper<ItemResponseBasic<UserProfileItem>>> {
        return proceedFlow {
            val response = source.createUser(request.toRequestCreateUser()).toItemResponseBasic()
            ItemResponseBasic(
                success = response.success,
                message = response.message,
                data = response.data
            )
        }.catch {
            emit(ResultWrapper.Error(Exception(it)))
        }.onStart {
            emit(ResultWrapper.Loading())
        }
    }

    override suspend fun createProducts(request: RequestCreateProductsItem): Flow<ResultWrapper<ItemResponseBasic<DataCreateProducts>>> {
        return proceedFlow {
            val response = source.createProducts(request).toItemResponseBasic()
            ItemResponseBasic(
                success = response.success,
                message = response.message,
                data = response.data
            )
        }.catch {
            emit(ResultWrapper.Error(Exception(it)))
        }.onStart {
            emit(ResultWrapper.Loading())
        }
    }

    override suspend fun updateProducts(
        id: String?,
        request: RequestCreateProductsItem
    ): Flow<ResultWrapper<ItemResponseBasic<DataCreateProducts>>> {
        return proceedFlow {
            val response = source.updateProducts(id, request).toItemResponseBasic()
            ItemResponseBasic(
                success = response.success,
                message = response.message,
                data = response.data
            )
        }.catch {
            emit(ResultWrapper.Error(Exception(it)))
        }.onStart {
            emit(ResultWrapper.Loading())
        }
    }

    override suspend fun getAllProducts(
        pageItem: Int?,
        limit: Int?,
        search: String?
    ): ResponseAllProductsItem {
        return source.getAllProducts(pageItem, limit, search)
    }

    override suspend fun getProductId(id: String?): Flow<ResultWrapper<ItemResponseBasic<ItemAllProducts>>> {
        return proceedFlow {
            val response = source.getProductId(id).toItemResponseBasic()
            ItemResponseBasic(
                success = response.success,
                message = response.message,
                data = response.data
            )
        }.catch {
            emit(ResultWrapper.Error(Exception(it)))
        }.onStart {
            emit(ResultWrapper.Loading())
        }
    }

    override suspend fun deleteProduct(id: String?): Flow<ResultWrapper<ItemResponseBasic<ItemAllProducts>>> {
        return proceedFlow {
            val response = source.deleteProduct(id).toItemResponseBasic()
            ItemResponseBasic(
                success = response.success,
                message = response.message,
                data = response.data
            )
        }.catch {
            emit(ResultWrapper.Error(Exception(it)))
        }.onStart {
            emit(ResultWrapper.Loading())
        }
    }

    override suspend fun createProductCategory(request: RequestProductsCategory): Flow<ResultWrapper<ItemResponseBasic<ResponseProductCategory>>> {
        return proceedFlow {
            val response = source.createProductCategory(request).toItemResponseBasic()
            ItemResponseBasic(
                success = response.success,
                message = response.message,
                data = response.data
            )
        }.catch {
            emit(ResultWrapper.Error(Exception(it)))
        }.onStart {
            emit(ResultWrapper.Loading())
        }
    }

    override suspend fun getAllProductCategories(
        pageItem: Int?,
        limit: Int?,
        search: String?
    ): ResponseAllProductCategory {
        return source.getAllProductCategories(pageItem, limit, search)
    }

    override suspend fun getProductCategoryById(id: String?): Flow<ResultWrapper<ItemResponseBasic<ResponseProductCategory>>> {
        return proceedFlow {
            val response = source.getProductCategoryById(id).toItemResponseBasic()
            ItemResponseBasic(
                success = response.success,
                message = response.message,
                data = response.data
            )
        }.catch {
            emit(ResultWrapper.Error(Exception(it)))
        }.onStart {
            emit(ResultWrapper.Loading())
        }
    }

    override suspend fun updateProductCategory(
        id: String?,
        request: RequestProductsCategory
    ): Flow<ResultWrapper<ItemResponseBasic<ResponseProductCategory>>> {
        return proceedFlow {
            val response = source.updateProductCategory(id, request).toItemResponseBasic()
            ItemResponseBasic(
                success = response.success,
                message = response.message,
                data = response.data
            )
        }.catch {
            emit(ResultWrapper.Error(Exception(it)))
        }.onStart {
            emit(ResultWrapper.Loading())
        }
    }

    override suspend fun deleteProductCategory(id: String?): Flow<ResultWrapper<ItemResponseBasic<ResponseProductCategory>>> {
        return proceedFlow {
            val response = source.deleteProductCategory(id).toItemResponseBasic()
            ItemResponseBasic(
                success = response.success,
                message = response.message,
                data = response.data
            )
        }.catch {
            emit(ResultWrapper.Error(Exception(it)))
        }.onStart {
            emit(ResultWrapper.Loading())
        }
    }
}
