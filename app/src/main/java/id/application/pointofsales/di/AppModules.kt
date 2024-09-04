package id.application.pointofsales.di

import com.chuckerteam.chucker.api.ChuckerInterceptor
import id.application.core.data.datasource.AppPreferenceDataSource
import id.application.core.data.datasource.AppPreferenceDataSourceImpl
import id.application.core.data.datasource.ApplicationDataSource
import id.application.core.data.datasource.ApplicationDataSourceImpl
import id.application.core.data.local.database.ApplicationDatabase
import id.application.core.data.network.interceptor.AuthInterceptor
import id.application.core.data.network.service.ApplicationService
import id.application.core.domain.repository.ApplicationRepository
import id.application.core.domain.repository.ApplicationRepositoryImpl
import id.application.core.utils.AssetWrapperApp
import id.application.core.utils.NetworkChangeReceiver
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

object AppModules {

    private val viewModelModule = module {
    }

    private val utilsModule = module {
        single { AssetWrapperApp(androidContext()) }
    }

    private val localModule = module {
//        single { androidContext().appDataSource }
//        single { ApplicationDatabase.getInstance(get()) }
//        single<PreferenceDataStoreHelper> { PreferenceDataStoreHelperImpl(get()) }
//        single { get<ApplicationDatabase>().blocksDao() }
//        single { get<ApplicationDatabase>().plantsDao() }
//        single { get<ApplicationDatabase>().geotagsOfflineDao() }

    }

    private val networkModule = module {
        single { ChuckerInterceptor(androidContext()) }
        single { AuthInterceptor(get(), get()) }
        single { ApplicationService.invoke(get(), get()) }
    }

    private val dataSourceModule = module {
        single<AppPreferenceDataSource> { AppPreferenceDataSourceImpl() }
        single<ApplicationDataSource> { ApplicationDataSourceImpl() }
    }

    private val repositoryModule = module {
        single<ApplicationRepository> {
            ApplicationRepositoryImpl()
        }
    }

    private val pagingSource = module {
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
        pagingSource,
        receiverModule,
    )
}