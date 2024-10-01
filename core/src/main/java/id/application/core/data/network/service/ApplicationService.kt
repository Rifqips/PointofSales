package id.application.core.data.network.service

import com.chuckerteam.chucker.api.ChuckerInterceptor
import id.application.core.BuildConfig
import id.application.core.data.network.interceptor.AuthInterceptor
import id.application.core.data.network.model.admin_user.RequestCreateUserItem
import id.application.core.data.network.model.admin_user.ResponseAllUsersItem
import id.application.core.data.network.model.auth.RequestLoginItem
import id.application.core.data.network.model.auth.ResponseLoginItem
import id.application.core.data.network.model.basic.ResponseBasicItem
import id.application.core.data.network.model.products.DataCreateProducts
import id.application.core.data.network.model.products.RequestCreateProductsItem
import id.application.core.data.network.model.products.ResponseAllProductsItem
import id.application.core.data.network.model.profile.ResponseProfileItem
import id.application.core.data.network.model.profile.UserProfileItem
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface ApplicationService {

    @POST("auth/login")
    suspend fun login(
        @Body request: RequestLoginItem
    ): ResponseBasicItem<ResponseLoginItem>

    @POST("auth/profile")
    suspend fun profile(): ResponseBasicItem<ResponseProfileItem>

    @GET("admin/users")
    suspend fun getAllUsers(
        @Query("page") pageItem: Int? = null,
    ): ResponseAllUsersItem

    @POST("admin/users")
    suspend fun createUser(
        @Body request: RequestCreateUserItem,
    ): ResponseBasicItem<UserProfileItem>

    @DELETE("admin/users/{id}")
    suspend fun deleteUserById(
        @Path("id") id: String? = null,
    ): ResponseBasicItem<ResponseProfileItem>

    @POST("products")
    suspend fun createProducts(
        @Body request: RequestCreateProductsItem,
    ): ResponseBasicItem<DataCreateProducts>


    @GET("products")
    suspend fun getAllProducts(
        @Query("page") pageItem: Int? = null,
        @Query("limit") limit: Int? = null,
        @Query("search") search: String? = null,
    ): ResponseAllProductsItem


    @DELETE("products/{id}")
    suspend fun getProductId(
        @Path("id") id: String? = null,
    ): ResponseBasicItem<ResponseProfileItem>

    @DELETE("products/{id}")
    suspend fun deleteProduct(
        @Path("id") id: String? = null,
    ): ResponseBasicItem<ResponseProfileItem>


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