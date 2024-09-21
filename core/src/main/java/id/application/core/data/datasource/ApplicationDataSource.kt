package id.application.core.data.datasource

import id.application.core.data.network.model.admin_user.RequestCreateUserItem
import id.application.core.data.network.model.admin_user.ResponseAllUsersItem
import id.application.core.data.network.model.auth.RequestLoginItem
import id.application.core.data.network.model.auth.ResponseLoginItem
import id.application.core.data.network.model.basic.ResponseBasicItem
import id.application.core.data.network.model.profile.ResponseProfileItem
import id.application.core.data.network.model.profile.UserProfileItem
import id.application.core.data.network.service.ApplicationService
import retrofit2.http.Body
import retrofit2.http.POST

interface ApplicationDataSource {

    suspend fun login(
        request: RequestLoginItem
    ): ResponseBasicItem<ResponseLoginItem>

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
}

class ApplicationDataSourceImpl(
    private val service: ApplicationService
): ApplicationDataSource {

    override suspend fun login(request: RequestLoginItem): ResponseBasicItem<ResponseLoginItem> {
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


}
