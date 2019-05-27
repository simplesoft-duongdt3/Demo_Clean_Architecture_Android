package com.duongdt3.domain.repository

import com.duongdt3.domain.model.BookDomainModel
import io.reactivex.Single

interface DemoBookRepository {
    fun getDemoBook(resultType: ResultType): Single<BookDomainModel>

    enum class ResultType {
        SUCCESS,
        SERVER_FAIL,
        SOMETHING_WRONG,
        TIME_OUT,
        NON_WRAPPED,
    }
}