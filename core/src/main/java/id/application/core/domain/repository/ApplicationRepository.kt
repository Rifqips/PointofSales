package id.application.core.domain.repository

import id.application.core.data.datasource.AppPreferenceDataSource
import id.application.core.data.datasource.ApplicationDataSource
import id.application.core.data.network.model.auth.ItemRequestLogin
import id.application.core.data.network.model.auth.ItemResponseLogin
import id.application.core.data.network.model.auth.toItemResponseLogin
import id.application.core.data.network.model.auth.toRequestLoginItem
import id.application.core.domain.model.admin_all_user.ItemResponseAllUsers
import id.application.core.domain.model.admin_all_user.toItemResponseAllUsers
import id.application.core.domain.model.basic.ItemResponseBasic
import id.application.core.domain.model.basic.toItemResponseBasic
import id.application.core.domain.model.profile.ItemResponseProfile
import id.application.core.domain.model.profile.toResponseProfileItem
import id.application.core.utils.ResultWrapper
import id.application.core.utils.proceedFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart

interface ApplicationRepository {

    suspend fun login(
        request: ItemRequestLogin
    ): Flow<ResultWrapper<ItemResponseBasic<ItemResponseLogin>>>

    suspend fun profile(): Flow<ResultWrapper<ItemResponseBasic<ItemResponseProfile>>>


    suspend fun getAllUsers(
        pageItem: Int? = null,
    ): ItemResponseAllUsers

}

class ApplicationRepositoryImpl(
    private val source : ApplicationDataSource,
    private val pref: AppPreferenceDataSource
): ApplicationRepository {

    override suspend fun login(request: ItemRequestLogin): Flow<ResultWrapper<ItemResponseBasic<ItemResponseLogin>>> {
        return proceedFlow {
            val response = source.login(request.toRequestLoginItem()).toItemResponseBasic()
            val mappedData = response.data?.toItemResponseLogin()
            mappedData?.accessToken?.let { pref.saveUserToken(it) }
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
}
