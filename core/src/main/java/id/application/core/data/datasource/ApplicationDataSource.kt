package id.application.core.data.datasource

import id.application.core.data.network.model.auth.RequestLoginItem
import id.application.core.data.network.model.auth.ResponseLoginItem
import id.application.core.data.network.model.basic.ResponseBasicItem
import id.application.core.data.network.service.ApplicationService

interface ApplicationDataSource {

    suspend fun login(
        request: RequestLoginItem
    ): ResponseBasicItem<ResponseLoginItem>
}

class ApplicationDataSourceImpl(
    private val service: ApplicationService
): ApplicationDataSource {

    override suspend fun login(request: RequestLoginItem): ResponseBasicItem<ResponseLoginItem> {
        return service.login(request)
    }
}
