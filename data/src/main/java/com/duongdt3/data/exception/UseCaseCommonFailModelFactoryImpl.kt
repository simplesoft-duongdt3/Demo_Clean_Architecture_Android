package com.duongdt3.data.exception

import com.duongdt3.domain.common.usecase.model.UseCaseFailModel
import com.duongdt3.domain.common.usecase.single.UseCaseCommonFailModelFactory
import retrofit2.HttpException
import java.net.SocketTimeoutException

class UseCaseCommonFailModelFactoryImpl : UseCaseCommonFailModelFactory {
    override fun createFailUseCaseModel(throwable: Throwable): UseCaseFailModel {
        return when (throwable) {
            is ServerException -> UseCaseFailModel.ServerFail(throwable.serverErrorCode, throwable.serverErrorMsg)

            is SocketTimeoutException -> UseCaseFailModel.NetworkTimeoutFail()

            is HttpException -> UseCaseFailModel.NetworkConnectionFail(throwable.code(), throwable.message())

            else -> UseCaseFailModel.UnknownFail(throwable.message)
        }
    }
}


