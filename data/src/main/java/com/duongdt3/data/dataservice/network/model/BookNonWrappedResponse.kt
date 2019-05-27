package com.duongdt3.data.dataservice.network.model

import com.google.gson.annotations.SerializedName

class BookNonWrappedResponse(
        @SerializedName("id")
        val id: Int?,
        @SerializedName("title")
        val title: String?
)