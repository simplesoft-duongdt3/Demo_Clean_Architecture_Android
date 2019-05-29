package com.duongdt3.data.dataservice.network.model

import com.google.gson.annotations.SerializedName

data class Book(
        @SerializedName("id")
        val id: Int?,
        @SerializedName("title")
        val title: String?
)

