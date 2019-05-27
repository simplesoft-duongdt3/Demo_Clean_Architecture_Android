package com.duongdt3.ui_mobile.common.dialog_provider

import androidx.appcompat.app.AppCompatActivity
import com.duongdt3.domain.common.usecase.model.UseCaseFailModel

class CommonErrorDialogProvider(val activity: AppCompatActivity) {
    private fun showNetworkConnectionDialog(networkConnectionFail: UseCaseFailModel.NetworkConnectionFail) {
        DialogUtil.showNetworkErrorDialog(activity)
    }

    private fun showNetworkTimeoutDialog(networkTimeoutFail: UseCaseFailModel.NetworkTimeoutFail) {
        DialogUtil.showNetworkTimeoutDialog(activity)
    }

    private fun showServerFailDialog(serverFail: UseCaseFailModel.ServerFail) {
        DialogUtil.showServerErrorDialog(activity, "Code: ${serverFail.errorCode} Msg: ${serverFail.errorMsg}")
    }

    private fun showUnknownFailDialog(unknownFail: UseCaseFailModel.UnknownFail) {
        DialogUtil.showUnknownProblemDialog(activity)
    }

    fun showErrorDialog(useCaseFailModel: UseCaseFailModel, handleFeatureError: ((UseCaseFailModel.FeatureFail) -> Unit)?) {
        when(useCaseFailModel) {
            is UseCaseFailModel.NetworkConnectionFail -> showNetworkConnectionDialog(useCaseFailModel)
            is UseCaseFailModel.NetworkTimeoutFail -> showNetworkTimeoutDialog(useCaseFailModel)
            is UseCaseFailModel.ServerFail -> showServerFailDialog(useCaseFailModel)
            is UseCaseFailModel.UnknownFail -> showUnknownFailDialog(useCaseFailModel)
            is UseCaseFailModel.FeatureFail -> handleFeatureError?.invoke(useCaseFailModel)
        }
    }
}