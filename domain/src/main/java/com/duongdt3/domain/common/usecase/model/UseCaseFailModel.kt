package com.duongdt3.domain.common.usecase.model

sealed class UseCaseFailModel {
    class NetworkConnectionFail(val errorCode: Int, val errorMsg: String?) : UseCaseFailModel()
    class NetworkTimeoutFail : UseCaseFailModel()
    class ServerFail(val errorCode: Int, val errorMsg: String?) : UseCaseFailModel()
    class UnknownFail(val errorMsg: String?) : UseCaseFailModel()
    abstract class FeatureFail : UseCaseFailModel()
}