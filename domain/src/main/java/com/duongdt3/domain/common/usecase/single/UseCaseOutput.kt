package com.duongdt3.domain.common.usecase.single

import com.duongdt3.domain.common.usecase.model.UseCaseFailModel
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class UseCaseOutput<SuccessOutput> internal constructor(private val useCaseExecution: UseCaseExecution, private val useCaseLogFailListener: UseCaseLogFailListener) {
    private var disposables: CompositeDisposable = CompositeDisposable()
    internal abstract fun buildUseCaseObservable(): Single<SuccessOutput>

    internal abstract fun createFailOutput(throwable: Throwable): UseCaseFailModel

    fun executeAsync(resultListener: ResultListener<SuccessOutput>): UseCaseTask {
        val observer: DefaultObserver<SuccessOutput> = DefaultObserver(this::createFailOutput)

        observer.addRawResultListener(UseCaseLogFailListener())
        observer.addResultListener(resultListener)

        val observable = this.buildUseCaseObservable()
                .subscribeOn(useCaseExecution.execution)
                .observeOn(useCaseExecution.postExecution)

        val disposable = observable.subscribeWith(observer)
        addDisposable(disposable)
        return UseCaseTask(disposable)
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