package com.duongdt3.data.dataservice.network.converter

import com.duongdt3.data.dataservice.network.model.WrappedResponse
import com.duongdt3.data.exception.ServerException
import io.reactivex.Single

fun <T> Single<WrappedResponse<T>>.handleServerErrorAndGetData(): Single<T> {
    return this.flatMap { response ->
        val header = response.header
        if (header?.resultCode == 200 && response.data != null) {
            Single.just(response.data)
        }
        val serverError = header?.resultCode ?: -1

        val serverMsg = header?.resultMessage

        Single.error<T>(ServerException(serverErrorCode = serverError, serverErrorMsg = serverMsg))
    }
}

fun <T> Single<WrappedResponse<T>>.handleServerError(): Single<WrappedResponse<T>> {
    return this.flatMap { response ->
        val header = response.header
        if (header?.resultCode == 200) {
            Single.just(response)
        }
        val serverError = header?.resultCode ?: -1

        val serverMsg = header?.resultMessage

        Single.error<WrappedResponse<T>>(ServerException(serverErrorCode = serverError, serverErrorMsg = serverMsg))
    }
}