package com.duongdt3.domain.model

class WebComicDomainModel(
        val id: Long,
        val title: String,
        val thumbnailUrl: String,
        val isFree: Boolean,
        val isTicket: Boolean,
        val isFullCharge: Boolean,
        val goodCount: Long,
        val author: String,
        val description: String,
        val isNew: Boolean,
        val isUpdated: Boolean,
        val isFinished: Boolean,
        val isRested: Boolean,
        val isDiscount: Boolean,
        val discountPercent: Int,
        val discountExpireDate: String
)