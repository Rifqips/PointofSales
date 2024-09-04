package id.application.core.utils.exceptions

import com.google.gson.Gson
import retrofit2.Response


class ApiException(
    override val message: String?,
    val httpCode: Int,
    val errorResponse: Response<*>?
) : Exception() {

}