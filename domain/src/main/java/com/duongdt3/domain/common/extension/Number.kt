package com.duongdt3.domain.common.extension

fun Int?.valueOrDefault(default: Int) : Int {
    return this ?: default
}

fun Int?.valueOrZero() : Int {
    return this.valueOrDefault(default = 0)
}

fun Long?.valueOrDefault(default: Long) : Long {
    return this ?: default
}

fun Long?.valueOrZero() : Long {
    return this.valueOrDefault(default = 0)
}

fun Double?.valueOrDefault(default: Double) : Double {
    return this ?: default
}

fun Double?.valueOrZero() : Double {
    return this.valueOrDefault(default = 0.0)
}