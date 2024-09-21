package id.application.core.domain.repository

import id.application.core.data.datasource.AppPreferenceDataSource
import id.application.core.data.datasource.ApplicationDataSource
import id.application.core.data.network.model.auth.ItemRequestLogin
import id.application.core.data.network.model.auth.ItemResponseLogin
import id.application.core.data.network.model.auth.toItemResponseLogin
import id.application.core.data.network.model.auth.toRequestLoginItem
import id.application.core.data.network.model.basic.toItemResponseBasic
import id.application.core.utils.ResultWrapper
import id.application.core.utils.proceedFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart

interface ApplicationRepository {

    suspend fun login(
        request: ItemRequestLogin
    ): Flow<ResultWrapper<ItemResponseLogin?>>
}

class ApplicationRepositoryImpl(
    private val source : ApplicationDataSource,
    private val pref: AppPreferenceDataSource
): ApplicationRepository {

    override suspend fun login(request: ItemRequestLogin): Flow<ResultWrapper<ItemResponseLogin?>> {
        return proceedFlow {
            source.login(request.toRequestLoginItem()).toItemResponseBasic().let {  basicResponse ->
                basicResponse.data?.toItemResponseLogin()
            }
        }.catch {
            emit(ResultWrapper.Error(Exception(it)))
        }.onStart {
            emit(ResultWrapper.Loading())
        }
    }
}
