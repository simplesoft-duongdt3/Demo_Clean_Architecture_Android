package com.duongdt3.data.repository

import com.duongdt3.data.dataservice.network.model.Book
import com.duongdt3.data.dataservice.network.service.DemoBookApiService
import com.duongdt3.domain.common.extension.valueOrEmpty
import com.duongdt3.domain.common.extension.valueOrZero
import com.duongdt3.domain.model.BookDomainModel
import com.duongdt3.domain.repository.DemoBookRepository
import io.reactivex.Single

class DemoBookRepositoryImpl(private val demoBookApiService: DemoBookApiService) : DemoBookRepository {
    override fun getDemoBook(resultType: DemoBookRepository.ResultType): Single<BookDomainModel> {
        return when (resultType) {
            DemoBookRepository.ResultType.SUCCESS -> getDemoBookSuccess()
            DemoBookRepository.ResultType.SERVER_FAIL -> getDemoBookServerFail()
            DemoBookRepository.ResultType.SOMETHING_WRONG -> getDemoBookSoomethingWrong()
            DemoBookRepository.ResultType.TIME_OUT -> getDemoBookTimeOut()
            DemoBookRepository.ResultType.NON_WRAPPED -> getDemoNonWrappedResponse()
        }
    }

    private fun getDemoNonWrappedResponse(): Single<BookDomainModel> {
        return demoBookApiService.getBookNonWrappedResponse()
                .flatMap { book ->
                    Single.just(BookDomainModel(
                            id = book.id.valueOrZero(),
                            title = book.title.valueOrEmpty()
                    ))
                }
    }

    private fun getDemoBookSuccess(): Single<BookDomainModel> {
        return demoBookApiService.getBookSuccess()
                .mapToDomainModel()
    }

    private fun getDemoBookServerFail(): Single<BookDomainModel> {
        return demoBookApiService.getBookServerFail()
                .mapToDomainModel()
    }

    private fun getDemoBookTimeOut(): Single<BookDomainModel> {
        return demoBookApiService.getBookTimeOut()
                .mapToDomainModel()
    }

    private fun getDemoBookSoomethingWrong(): Single<BookDomainModel> {
        return demoBookApiService.getBookSomethingWrong()
                .mapToDomainModel()
    }

    private fun Single<Book>.mapToDomainModel(): Single<BookDomainModel> {
        return this.flatMap { response ->
            Single.just(BookDomainModel(
                    id = response.id.valueOrZero(),
                    title = response.title.valueOrEmpty()
            ))
        }
    }
}