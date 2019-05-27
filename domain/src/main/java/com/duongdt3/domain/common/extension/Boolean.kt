package com.duongdt3.domain.common.extension

fun Boolean?.valueOrDefault(default: Boolean) : Boolean {
    return this ?: default
}

fun Boolean?.valueOrFalse() : Boolean {
    return this.valueOrDefault(default = false)
}