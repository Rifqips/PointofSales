package id.application.core.utils.exceptions

import retrofit2.Response


class ApiException(
    override val message: String?,
    val httpCode: Int,
    val errorResponse: Response<*>?
) : Exception() {

}