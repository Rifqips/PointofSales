package id.application.pointofsales.di

import com.chuckerteam.chucker.api.ChuckerInterceptor
import id.application.core.data.datasource.AppPreferenceDataSource
import id.application.core.data.datasource.AppPreferenceDataSourceImpl
import id.application.core.data.datasource.ApplicationDataSource
import id.application.core.data.datasource.ApplicationDataSourceImpl
import id.application.core.data.local.database.ApplicationDatabase
import id.application.core.data.local.datastore.PreferenceDataStoreHelper
import id.application.core.data.local.datastore.PreferenceDataStoreHelperImpl
import id.application.core.data.local.datastore.appDataSource
import id.application.core.data.network.interceptor.AuthInterceptor
import id.application.core.data.network.service.ApplicationService
import id.application.core.domain.repository.ApplicationRepository
import id.application.core.domain.repository.ApplicationRepositoryImpl
import id.application.core.domain.repository.ApplicationRoomRepository
import id.application.core.domain.repository.ApplicationRoomRepositoryImpl
import id.application.core.utils.AssetWrapperApp
import id.application.core.utils.NetworkChangeReceiver
import id.application.pointofsales.presentation.viewmodel.VmApplication
import id.application.pointofsales.presentation.viewmodel.VmRoomApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module

object AppModules {

    private val networkModule = module {
        single { ChuckerInterceptor(androidContext()) }
        single { AuthInterceptor(get(), get()) }
        single { ApplicationService.invoke(get(), get()) }
    }

    private val viewModelModule = module {
        viewModelOf(::VmApplication)
        viewModelOf(::VmRoomApplication)
    }

    private val dataSourceModule = module {
        single<AppPreferenceDataSource> { AppPreferenceDataSourceImpl(get()) }
        single<ApplicationDataSource> { ApplicationDataSourceImpl(get()) }
    }

    private val repositoryModule = module {
        single<ApplicationRepository> {
            ApplicationRepositoryImpl(
                get(),
                get(),
            )
        }
        single<ApplicationRoomRepository> { ApplicationRoomRepositoryImpl(get()) }
    }

    private val localModule = module {
        single { androidContext().appDataSource }
        single { ApplicationDatabase.getInstance(get()) }
        single<PreferenceDataStoreHelper> { PreferenceDataStoreHelperImpl(get()) }
        single { get<ApplicationDatabase>().cartDao() }
    }

    private val utilsModule = module {
        single { AssetWrapperApp(androidContext()) }
    }

    private val receiverModule = module {
        factory { (onNetworkAvailable: () -> Unit) -> NetworkChangeReceiver(onNetworkAvailable) }
    }

    val modules: List<Module> = listOf(
        viewModelModule,
        utilsModule,
        localModule,
        networkModule,
        dataSourceModule,
        repositoryModule,
        utilsModule,
        receiverModule,
    )
}