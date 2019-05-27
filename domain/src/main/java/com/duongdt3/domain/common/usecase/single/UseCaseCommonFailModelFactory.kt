package com.duongdt3.domain.common.usecase.single

import com.duongdt3.domain.common.usecase.model.UseCaseFailModel

interface UseCaseCommonFailModelFactory {
    fun createFailUseCaseModel(throwable: Throwable) : UseCaseFailModel
}