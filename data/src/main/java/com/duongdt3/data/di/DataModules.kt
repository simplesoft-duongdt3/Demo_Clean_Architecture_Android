package com.duongdt3.data.di

import android.content.Context
import com.duongdt3.data.dataservice.network.converter.WrappedResponseConverterFactory
import com.duongdt3.data.dataservice.network.model.BookNonWrappedResponse
import com.duongdt3.data.dataservice.network.service.DemoBookApiService
import com.duongdt3.data.exception.UseCaseCommonFailModelFactoryImpl
import com.duongdt3.data.repository.DemoBookRepositoryImpl
import com.duongdt3.data.util.OkHttpClientFactory
import com.duongdt3.domain.common.usecase.single.UseCaseCommonFailModelFactory
import com.duongdt3.domain.repository.DemoBookRepository
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
        fun createDemoBookApiService(context: Context): DemoBookApiService {
            val apiUrl = "https://demobook.free.beeceptor.com/"

            //create gson default converter factory
            val gsonConverterFactory = GsonConverterFactory.create()

            //add nonWrappedModelClasses
            val nonWrappedModelClasses = listOf<Class<*>>(BookNonWrappedResponse::class.java)

            //create wrappedResponseConverterFactory
            val wrappedResponseConverterFactory = WrappedResponseConverterFactory(gsonConverterFactory, nonWrappedModelClasses)

            val build = Retrofit.Builder()
                    .client(okHttpClientFactory.createTrustHttpsClient(context = context))
                    .baseUrl(apiUrl)
                    .addConverterFactory(wrappedResponseConverterFactory)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()

            return build.create(DemoBookApiService::class.java)
        }

        single {
            createDemoBookApiService(context = get())
        }

        single<DemoBookRepository> {
            DemoBookRepositoryImpl(
                    demoBookApiService = get()
            )
        }
    }

}
