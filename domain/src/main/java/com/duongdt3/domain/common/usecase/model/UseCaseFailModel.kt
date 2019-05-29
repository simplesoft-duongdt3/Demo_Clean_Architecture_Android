package com.duongdt3.domain.common.usecase.model

sealed class UseCaseFailModel {
    //Network connection error
    class NetworkConnectionFail(val errorCode: Int, val errorMsg: String?) : UseCaseFailModel()

    //Network timeout error
    class NetworkTimeoutFail : UseCaseFailModel()

    //Internal server error
    class ServerFail(val errorCode: Int, val errorMsg: String?) : UseCaseFailModel()

    //Common error
    class UnknownFail(val errorMsg: String?) : UseCaseFailModel()

    //Feature specify error
    abstract class FeatureFail : UseCaseFailModel()
}

