package com.duongdt3.domain.common.usecase.single

import com.duongdt3.domain.common.usecase.model.UseCaseFailModel

interface ResultListener<in Result> {
    fun success(data: Result)
    fun fail(error: UseCaseFailModel)
}

