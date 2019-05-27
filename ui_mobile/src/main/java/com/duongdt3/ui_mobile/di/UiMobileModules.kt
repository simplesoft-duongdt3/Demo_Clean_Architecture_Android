package com.duongdt3.ui_mobile.di

import com.duongdt3.domain.common.log.AppLogger
import com.duongdt3.domain.common.usecase.single.UseCaseExecution
import com.duongdt3.ui_mobile.BuildConfig
import com.duongdt3.ui_mobile.common.executor.AndroidUseCaseExecution
import com.duongdt3.ui_mobile.common.logger.TimberAppLogger
import org.koin.core.module.Module
import org.koin.dsl.module

object UiMobileModules {
    fun getModules() = mutableListOf<Module>().apply {
        add(useCaseModule)
        add(loggerModule)
    }

    private val loggerModule = module {
        single<AppLogger> {
            TimberAppLogger(BuildConfig.DEBUG)
        }
    }

    private val useCaseModule = module {
        single<UseCaseExecution> {
            AndroidUseCaseExecution()
        }
    }
}

