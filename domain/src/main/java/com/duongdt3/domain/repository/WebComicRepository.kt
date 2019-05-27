package com.duongdt3.domain.repository

import com.duongdt3.domain.model.WebComicDomainModel
import io.reactivex.Single

interface WebComicRepository {
    fun getAllWebComicList(): Single<List<WebComicDomainModel>>
    fun getWebComicSortPopularList(): Single<List<WebComicDomainModel>>
    fun getWebComicSortRelevancyList(): Single<List<WebComicDomainModel>>
}