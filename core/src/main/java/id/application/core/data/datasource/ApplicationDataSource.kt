package id.application.core.data.datasource

import android.util.Log
import id.application.core.data.network.model.admin_user.RequestCreateUserItem
import id.application.core.data.network.model.admin_user.ResponseAllUsersItem
import id.application.core.data.network.model.auth.RequestLoginItem
import id.application.core.data.network.model.auth.ResponseLoginItem
import id.application.core.data.network.model.basic.ResponseBasicItem
import id.application.core.data.network.model.products.DataCreateProducts
import id.application.core.data.network.model.products.ItemAllProducts
import id.application.core.data.network.model.products.RequestCreateProductsItem
import id.application.core.data.network.model.products.ResponseAllProductsItem
import id.application.core.data.network.model.products.category.RequestProductsCategory
import id.application.core.data.network.model.products.category.ResponseAllProductCategory
import id.application.core.data.network.model.products.category.ResponseProductCategory
import id.application.core.data.network.model.profile.ResponseProfileItem
import id.application.core.data.network.model.profile.UserProfileItem
import id.application.core.data.network.service.ApplicationService

interface ApplicationDataSource {

    suspend fun login(
        request: RequestLoginItem
    ): ResponseLoginItem

    suspend fun profile(): ResponseBasicItem<ResponseProfileItem>

    suspend fun getAllUsers(
        pageItem: Int? = null,
    ): ResponseAllUsersItem

    suspend fun deleteUserById(
        id: String? = null,
    ): ResponseBasicItem<ResponseProfileItem>

    suspend fun createUser(
        request: RequestCreateUserItem,
    ): ResponseBasicItem<UserProfileItem>

    suspend fun createProducts(
        request: RequestCreateProductsItem,
    ): ResponseBasicItem<DataCreateProducts>

    suspend fun updateProducts(
        id : String? = null,
        request: RequestCreateProductsItem,
    ): ResponseBasicItem<DataCreateProducts>

    suspend fun getAllProducts(
        pageItem: Int? = null,
        limit: Int? = null,
        search: String? = null,
    ): ResponseAllProductsItem

    suspend fun getProductId(
        id: String? = null,
    ): ResponseBasicItem<ItemAllProducts>

    suspend fun deleteProduct(
        id: String? = null,
    ): ResponseBasicItem<ItemAllProducts>

    suspend fun createProductCategory(
       request: RequestProductsCategory,
    ): ResponseBasicItem<ResponseProductCategory>

    suspend fun getAllProductCategories(
        pageItem: Int? = null,
        limit: Int? = null,
        search: String? = null,
    ): ResponseAllProductCategory

    suspend fun getProductCategoryById(
        id: String? = null,
    ):  ResponseBasicItem<ResponseProductCategory>

    suspend fun updateProductCategory(
        id: String? = null,
        request: RequestProductsCategory,
    ): ResponseBasicItem<ResponseProductCategory>

    suspend fun deleteProductCategory(
        id: String? = null,
    ): ResponseBasicItem<ResponseProductCategory>
}

class ApplicationDataSourceImpl(
    private val service: ApplicationService
): ApplicationDataSource {

    override suspend fun login(request: RequestLoginItem): ResponseLoginItem {
        Log.d("check-login", "from source $request")
        return service.login(request)
    }

    override suspend fun profile(): ResponseBasicItem<ResponseProfileItem> {
        return service.profile()
    }

    override suspend fun getAllUsers(
        pageItem: Int?,
    ): ResponseAllUsersItem {
        return service.getAllUsers(pageItem)
    }

    override suspend fun deleteUserById(id: String?): ResponseBasicItem<ResponseProfileItem> {
        return service.deleteUserById(id)
    }

    override suspend fun createUser(request: RequestCreateUserItem): ResponseBasicItem<UserProfileItem> {
        return service.createUser(request)
    }

    override suspend fun createProducts(request: RequestCreateProductsItem): ResponseBasicItem<DataCreateProducts> {
        return service.createProducts(request)
    }

    override suspend fun updateProducts(id: String?, request: RequestCreateProductsItem): ResponseBasicItem<DataCreateProducts> {
        return service.updateProducts(id, request)
    }

    override suspend fun getAllProducts(
        pageItem: Int?,
        limit: Int?,
        search: String?
    ): ResponseAllProductsItem {
        return service.getAllProducts(pageItem, limit, search)
    }

    override suspend fun getProductId(id: String?): ResponseBasicItem<ItemAllProducts> {
        return service.getProductId(id)
    }

    override suspend fun deleteProduct(id: String?): ResponseBasicItem<ItemAllProducts> {
        return service.deleteProductById(id)
    }

    override suspend fun createProductCategory(request: RequestProductsCategory): ResponseBasicItem<ResponseProductCategory> {
        return service.createProductCategory(request)
    }

    override suspend fun getAllProductCategories(
        pageItem: Int?,
        limit: Int?,
        search: String?
    ): ResponseAllProductCategory {
        return service.getAllProductCategories(pageItem, limit, search)
    }

    override suspend fun getProductCategoryById(id: String?): ResponseBasicItem<ResponseProductCategory> {
        return service.getProductCategoryById(id)
    }

    override suspend fun updateProductCategory(
        id: String?,
        request: RequestProductsCategory
    ): ResponseBasicItem<ResponseProductCategory> {
        return service.updateProductCategory(id, request)
    }

    override suspend fun deleteProductCategory(id: String?): ResponseBasicItem<ResponseProductCategory> {
        return service.deleteProductCategory(id)
    }
}
