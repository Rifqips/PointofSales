package id.application.core.data.network.interceptor

import android.content.Context
import id.application.core.BuildConfig
import id.application.core.data.datasource.AppPreferenceDataSource
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response


class AuthInterceptor(
    private val ctx: Context,
    private val preference: AppPreferenceDataSource
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val requestBuilder = originalRequest.newBuilder()
        requestBuilder.addHeader("accept", "application/json")
        requestBuilder.addHeader("Content-Type", "application/json")
        val token = runBlocking { preference.getUserToken() }
        if (token.isNotEmpty()) {
            requestBuilder.addHeader("Bearer", token)
        }
        val modifiedRequest = requestBuilder.build()
        val response = chain.proceed(modifiedRequest)

        return response
    }
}
