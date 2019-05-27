package com.duongdt3.domain.common.usecase.single

import com.duongdt3.domain.common.usecase.model.UseCaseFailModel
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class UseCase<in Input, SuccessOutput> internal constructor(private val useCaseExecution: UseCaseExecution, private val useCaseCommonFailModelFactory: UseCaseCommonFailModelFactory) {
    private var disposables: CompositeDisposable = CompositeDisposable()

    /**
     * Builds an [Observable] which will be used when executing the current [UseCase].
     */
    internal abstract fun buildUseCaseObservable(input: Input): Single<SuccessOutput>

    internal abstract fun createFeatureFailOutput(throwable: Throwable): UseCaseFailModel.FeatureFail?

    private fun createCommonFailOutput(throwable: Throwable): UseCaseFailModel {
        return useCaseCommonFailModelFactory.createFailUseCaseModel(throwable)
    }

    fun executeAsync(resultListener: ResultListener<SuccessOutput>, input: Input): UseCaseTask {
        val observer: DefaultObserver<SuccessOutput> = DefaultObserver(this::createFailOutput)

        observer.addRawResultListener(UseCaseLogFailListener())
        observer.addResultListener(resultListener)

        val observable = this.buildUseCaseObservable(input)
                .subscribeOn(useCaseExecution.execution)
                .observeOn(useCaseExecution.postExecution)

        val disposable = observable.subscribeWith(observer)
        addDisposable(disposable)
        return UseCaseTask(disposable)
    }

    private fun createFailOutput(throwable: Throwable): UseCaseFailModel {
        return createFeatureFailOutput(throwable) ?: createCommonFailOutput(throwable)
    }

    fun cancel() {
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
        disposables = CompositeDisposable()
    }

    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }
}
