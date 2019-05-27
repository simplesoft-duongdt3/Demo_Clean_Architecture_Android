package com.duongdt3.presentation_mvp.features.demo_book;

import com.duongdt3.domain.common.usecase.model.UseCaseFailModel
import com.duongdt3.domain.common.usecase.single.ResultListener
import com.duongdt3.domain.repository.DemoBookRepository
import com.duongdt3.domain.usecase.demo_book.GetDemoBookUseCase
import com.duongdt3.domain.usecase.demo_book.GetDemoBookUseCaseInputModel
import com.duongdt3.domain.usecase.demo_book.GetDemoBookUseCaseSuccessModel

class DemoBookPresenter(
        private val getDemoBookUseCase: GetDemoBookUseCase
) : DemoBookContract.Presenter() {
    private val getBookResultListener = object : ResultListener<GetDemoBookUseCaseSuccessModel> {
        override fun success(data: GetDemoBookUseCaseSuccessModel) {
            val book = data.book
            val demoBookPresentationModel = DemoBookPresentationModel(
                    id = book.id,
                    title = book.title
            )
            view?.showResult(demoBookPresentationModel)
            view?.hideLoading()
        }

        override fun fail(error: UseCaseFailModel) {
            view?.showError(error)
            view?.hideLoading()
        }

    }

    override fun onStart() {
        //nothing to do
    }

    override fun onGetBookSuccessClicked() {
        view?.showLoading()
        getDemoBookUseCase.cancel()
        getDemoBookUseCase.executeAsync(
                resultListener = getBookResultListener,
                input = GetDemoBookUseCaseInputModel(DemoBookRepository.ResultType.SUCCESS)
        )
    }

    override fun onGetBookServerFailClicked() {
        view?.showLoading()
        getDemoBookUseCase.cancel()
        getDemoBookUseCase.executeAsync(
                resultListener = getBookResultListener,
                input = GetDemoBookUseCaseInputModel(DemoBookRepository.ResultType.SERVER_FAIL)
        )
    }

    override fun onGetBookTimeoutClicked() {
        view?.showLoading()
        getDemoBookUseCase.cancel()
        getDemoBookUseCase.executeAsync(
                resultListener = getBookResultListener,
                input = GetDemoBookUseCaseInputModel(DemoBookRepository.ResultType.TIME_OUT)
        )
    }

    override fun onGetBookSomethingWrongClicked() {
        view?.showLoading()
        getDemoBookUseCase.cancel()
        getDemoBookUseCase.executeAsync(
                resultListener = getBookResultListener,
                input = GetDemoBookUseCaseInputModel(DemoBookRepository.ResultType.SOMETHING_WRONG)
        )
    }

    override fun onGetNonWrappedResponseClicked() {
        view?.showLoading()
        getDemoBookUseCase.cancel()
        getDemoBookUseCase.executeAsync(
                resultListener = getBookResultListener,
                input = GetDemoBookUseCaseInputModel(DemoBookRepository.ResultType.NON_WRAPPED)
        )
    }

    override fun onStop() {
        getDemoBookUseCase.cancel()
    }

}