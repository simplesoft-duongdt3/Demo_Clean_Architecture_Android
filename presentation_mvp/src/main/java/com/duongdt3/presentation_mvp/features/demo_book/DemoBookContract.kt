package com.duongdt3.presentation_mvp.features.demo_book;

import com.duongdt3.domain.common.usecase.model.UseCaseFailModel
import com.duongdt3.presentation_mvp.common.MvpPresenter
import com.duongdt3.presentation_mvp.common.MvpView

class DemoBookContract {
    abstract class Presenter : MvpPresenter<View>() {
        abstract fun onGetBookSuccessClicked()
        abstract fun onGetBookServerFailClicked()
        abstract fun onGetBookTimeoutClicked()
        abstract fun onGetBookSomethingWrongClicked()
        abstract fun onGetNonWrappedResponseClicked()

    }

    interface View : MvpView {
        fun showLoading()
        fun hideLoading()
        fun showError(failModel: UseCaseFailModel)
        fun showResult(demoBookPresentationModel: DemoBookPresentationModel)
    }
}