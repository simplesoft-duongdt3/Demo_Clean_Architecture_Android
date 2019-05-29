package com.duongdt3.data.dataservice.network.model

import com.google.gson.annotations.SerializedName

data class WrappedResponse<T>(
        @SerializedName("data")
        val `data`: T?,
        @SerializedName("header")
        val header: Header?
) {

    data class Header(
            @SerializedName("isSuccessful")
            val isSuccessful: Boolean?,
            @SerializedName("resultCode")
            val resultCode: Int?,
            @SerializedName("resultMessage")
            val resultMessage: String?
    )
}


