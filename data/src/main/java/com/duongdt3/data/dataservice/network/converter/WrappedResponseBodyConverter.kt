package com.duongdt3.data.dataservice.network.converter

import com.duongdt3.data.dataservice.network.model.WrappedResponse
import com.duongdt3.data.exception.ServerException
import okhttp3.ResponseBody
import retrofit2.Converter
import java.io.IOException

class WrappedResponseBodyConverter<T>(private val converter: Converter<ResponseBody, WrappedResponse<T>>) : Converter<ResponseBody, T> {

    @Throws(IOException::class)
    override fun convert(value: ResponseBody): T {
        val response = converter.convert(value)
        val header = response?.header
        if (header?.isSuccessful != null && header.isSuccessful && response.data != null) {
            return response.data
        }

        val serverError = header?.resultCode ?: -1
        val serverMsg = header?.resultMessage
        throw ServerException(serverError, serverMsg)
    }
}