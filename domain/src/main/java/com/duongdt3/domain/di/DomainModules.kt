package com.duongdt3.domain.di

import com.duongdt3.domain.usecase.demo_book.GetDemoBookUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

object DomainModules {
    fun getModules() = mutableListOf<Module>().apply {
        add(demoBookModule)
    }

    private val demoBookModule = module {

        factory {
            GetDemoBookUseCase(
                    demoBookRepository = get(),
                    useCaseExecution = get(),
                    useCaseCommonFailModelFactory = get()
            )
        }
    }
}
