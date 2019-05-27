package com.duongdt3.domain.common.usecase.single

interface RawResultListener {
    fun fail(throwable: Throwable)
}