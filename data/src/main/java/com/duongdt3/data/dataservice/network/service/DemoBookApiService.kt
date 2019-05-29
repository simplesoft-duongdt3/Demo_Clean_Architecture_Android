package com.duongdt3.data.dataservice.network.service

import com.duongdt3.data.dataservice.network.model.Book
import com.duongdt3.data.dataservice.network.model.BookNonWrappedResponse
import io.reactivex.Single
import retrofit2.http.GET

interface DemoBookApiService {
    @GET("book?result=success")
    fun getBookSuccess(): Single<Book>

    @GET("book?result=server_fail")
    fun getBookServerFail(): Single<Book>

    @GET("book?result=something_wrong")
    fun getBookSomethingWrong(): Single<Book>

    @GET("book?result=time_out")
    fun getBookTimeOut(): Single<Book>

    @GET("no-wrapped-response")
    fun getBookNonWrappedResponse(): Single<BookNonWrappedResponse>
}



