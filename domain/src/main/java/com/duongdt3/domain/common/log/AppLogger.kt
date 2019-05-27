package com.duongdt3.domain.common.log

interface AppLogger {
    fun i(tag: String, message: String, vararg args: Any)

    fun i(tag: String, t: Throwable, message: String, vararg args: Any)

    fun i(tag: String, t: Throwable)

    fun d(tag: String, message: String, vararg args: Any)

    fun d(tag: String, t: Throwable, message: String, vararg args: Any)

    fun d(tag: String, t: Throwable)

    fun e(tag: String, message: String, vararg args: Any)

    fun e(tag: String, t: Throwable, message: String, vararg args: Any)

    fun e(tag: String, t: Throwable)
}