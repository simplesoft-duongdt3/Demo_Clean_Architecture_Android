package com.duongdt3.domain.common.usecase.single

import io.reactivex.disposables.Disposable

class UseCaseTask(private val disposable: Disposable) {

    fun cancel() {
        disposable.dispose()
    }
}