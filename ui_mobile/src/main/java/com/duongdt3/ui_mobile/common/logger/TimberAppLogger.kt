package com.duongdt3.ui_mobile.common.logger

import com.duongdt3.domain.common.log.AppLogger
import timber.log.Timber

class TimberAppLogger(private val isLog: Boolean) : AppLogger {
    override fun i(tag: String, message: String, vararg args: Any) {
        if (isLog) {
            Timber.tag(tag).i(message, args)
        }
    }

    override fun i(tag: String, t: Throwable, message: String, vararg args: Any) {
        if (isLog) {
            Timber.tag(tag).i(t, message, args)
        }
    }

    override fun i(tag: String, t: Throwable) {
        if (isLog) {
            Timber.tag(tag).i(t)
        }
    }

    override fun d(tag: String, message: String, vararg args: Any) {
        if (isLog) {
            Timber.tag(tag).d(message, args)
        }
    }

    override fun d(tag: String, t: Throwable, message: String, vararg args: Any) {
        if (isLog) {
            Timber.tag(tag).d(t, message, args)
        }
    }

    override fun d(tag: String, t: Throwable) {
        if (isLog) {
            Timber.tag(tag).d(t)
        }
    }

    override fun e(tag: String, message: String, vararg args: Any) {
        if (isLog) {
            Timber.tag(tag).e(message, args)
        }
    }

    override fun e(tag: String, t: Throwable, message: String, vararg args: Any) {
        if (isLog) {
            Timber.tag(tag).e(t, message, args)
        }
    }

    override fun e(tag: String, t: Throwable) {
        if (isLog) {
            Timber.tag(tag).e(t)
        }
    }
}