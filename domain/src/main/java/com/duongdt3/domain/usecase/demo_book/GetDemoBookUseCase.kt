package com.duongdt3.domain.usecase.demo_book;

import com.duongdt3.domain.common.usecase.model.UseCaseFailModel
import com.duongdt3.domain.common.usecase.single.UseCase
import com.duongdt3.domain.common.usecase.single.UseCaseCommonFailModelFactory
import com.duongdt3.domain.common.usecase.single.UseCaseExecution
import com.duongdt3.domain.repository.DemoBookRepository
import io.reactivex.Single

class GetDemoBookUseCase(
        private val demoBookRepository: DemoBookRepository,
        useCaseExecution: UseCaseExecution,
        useCaseCommonFailModelFactory: UseCaseCommonFailModelFactory
) : UseCase<GetDemoBookUseCaseInputModel, GetDemoBookUseCaseSuccessModel>(
        useCaseExecution,
        useCaseCommonFailModelFactory
) {
    override fun buildUseCaseObservable(input: GetDemoBookUseCaseInputModel): Single<GetDemoBookUseCaseSuccessModel> {
        return demoBookRepository.getDemoBook(input.resultType)
                .flatMap { book ->
                    Single.just(GetDemoBookUseCaseSuccessModel(book = book))
                }
    }

    override fun createFeatureFailOutput(throwable: Throwable): UseCaseFailModel.FeatureFail? = null
}