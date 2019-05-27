package com.duongdt3.data.di

import com.duongdt3.data.BuildConfig
import com.duongdt3.data.dataservice.network.converter.WrappedResponseConverterFactory
import com.duongdt3.data.dataservice.network.model.BookNonWrappedResponse
import com.duongdt3.data.dataservice.network.service.DemoBookApiService
import com.duongdt3.data.exception.UseCaseCommonFailModelFactoryImpl
import com.duongdt3.data.repository.DemoBookRepositoryImpl
import com.duongdt3.data.util.OkHttpClientFactory
import com.duongdt3.domain.common.usecase.single.UseCaseCommonFailModelFactory
import com.duongdt3.domain.repository.DemoBookRepository
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object DataModules {
    fun getModules() = mutableListOf<Module>().apply {
        add(useCaseCommonFailModelFactoryModule)
        add(demoBookApiServiceModule)
    }

    private val useCaseCommonFailModelFactoryModule = module {
        single<UseCaseCommonFailModelFactory> {
            UseCaseCommonFailModelFactoryImpl()
        }
    }

    private val demoBookApiServiceModule = module {
        val okHttpClientFactory = OkHttpClientFactory()
        fun createDemoBookApiService(): DemoBookApiService {
            val apiUrl = "https://demobook.free.beeceptor.com/"
            val gsonConverterFactory = GsonConverterFactory.create()
            val excludeClasses = listOf<Class<*>>(BookNonWrappedResponse::class.java)
            val wrappedResponseConverterFactory = WrappedResponseConverterFactory(gsonConverterFactory, excludeClasses)
            val build = Retrofit.Builder()
                    .client(okHttpClientFactory.createTrustHttpsClient())
                    .baseUrl(apiUrl)
                    .addConverterFactory(wrappedResponseConverterFactory)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()

            return build.create(DemoBookApiService::class.java)
        }

        single {
            createDemoBookApiService()
        }

        single<DemoBookRepository> {
            DemoBookRepositoryImpl(
                    demoBookApiService = get()
            )
        }
    }

}
