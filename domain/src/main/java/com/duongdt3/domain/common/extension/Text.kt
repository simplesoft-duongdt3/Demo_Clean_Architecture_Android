package com.duongdt3.domain.common.extension

fun String?.valueOrDefault(default: String) : String {
    return this ?: default
}

fun String?.valueOrEmpty() : String {
    return this.valueOrDefault(default = "")
}