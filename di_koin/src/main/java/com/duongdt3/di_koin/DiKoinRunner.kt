package com.duongdt3.di_koin

import android.app.Application
import android.content.Context
import com.duongdt3.data.di.DataModules
import com.duongdt3.domain.di.DomainModules
import com.duongdt3.presentation_mvp.di.PresentationMvpModules
import com.duongdt3.ui_mobile.BuildConfig
import com.duongdt3.ui_mobile.di.UiMobileModules
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.logger.Logger
import org.koin.core.logger.MESSAGE
import org.koin.core.module.Module
import org.koin.dsl.module
import timber.log.Timber

object DiKoinRunner {
    @JvmStatic
    fun initDi(application: Application) {

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            //TODO impl release Timber tree
        }

        val androidContextModule = module {
            single<Context> { application }
        }

        val modules = mutableListOf<Module>().apply {
            add(androidContextModule)
            addAll(DomainModules.getModules())
            addAll(DataModules.getModules())
            addAll(PresentationMvpModules.getModules())
            addAll(UiMobileModules.getModules())
        }

        val koinLogger = KoinLogger()
        startKoin {
            modules(modules)
            logger(koinLogger)
        }
    }

    class KoinLogger: Logger() {
        override fun log(level: Level, msg: MESSAGE) {
            when {
                level >= Level.ERROR -> Timber.e(msg)
                level >= Level.DEBUG -> Timber.d(msg)
                else -> Timber.i(msg)
            }
        }
    }
}