package com.duongdt3.presentation_mvp.di

import com.duongdt3.presentation_mvp.features.demo_book.DemoBookContract
import com.duongdt3.presentation_mvp.features.demo_book.DemoBookPresenter
import org.koin.core.module.Module
import org.koin.dsl.module

object PresentationMvpModules {
    fun getModules() = mutableListOf<Module>().apply {
        add(demoBookModule)
    }

    private val demoBookModule = module {
        factory<DemoBookContract.Presenter> {
            DemoBookPresenter(
                getDemoBookUseCase = get()
            )
        }
    }
}