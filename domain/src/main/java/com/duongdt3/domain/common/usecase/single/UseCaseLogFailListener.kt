package com.duongdt3.domain.common.usecase.single


class UseCaseLogFailListener : RawResultListener {
    override fun fail(throwable: Throwable) {
        //LogDebug.d(throwable, throwable.message.orEmpty())
    }
}