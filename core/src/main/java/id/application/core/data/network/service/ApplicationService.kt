package id.application.core.data.network.service

import com.chuckerteam.chucker.api.ChuckerInterceptor
import id.application.core.BuildConfig
import id.application.core.data.network.interceptor.AuthInterceptor
import id.application.core.data.network.model.auth.RequestLoginItem
import id.application.core.data.network.model.auth.ResponseLoginItem
import id.application.core.data.network.model.basic.ResponseBasicItem
import id.application.core.data.network.model.profile.ResponseProfileItem
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

interface ApplicationService {

    @POST("auth/login")
    suspend fun login(
        @Body request: RequestLoginItem
    ): ResponseBasicItem<ResponseLoginItem>

    @POST("auth/profile")
    suspend fun profile(): ResponseBasicItem<ResponseProfileItem>

    companion object{
        @JvmStatic
        operator fun invoke(
            chucker: ChuckerInterceptor,
            authInterceptor: AuthInterceptor,
        ): ApplicationService {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(authInterceptor)
                .addInterceptor(chucker)
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
            return retrofit.create(ApplicationService::class.java)
        }
    }
}