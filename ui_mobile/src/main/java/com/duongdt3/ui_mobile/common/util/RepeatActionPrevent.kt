package com.duongdt3.ui_mobile.common.util

import android.os.SystemClock

import androidx.collection.ArrayMap
const val REPEAT_ACTION_PREVENT_DEFAULT_MILLISECONDS = 300L
class RepeatActionPrevent @JvmOverloads constructor(private val minTime: Long = REPEAT_ACTION_PREVENT_DEFAULT_MILLISECONDS) {
    private val touchActionMap = ArrayMap<String, Long>()

    fun checkRepeatAction(actionKey: String): Boolean {
        var result = false
        val currentTime = SystemClock.elapsedRealtime()

        var lastTimeAction = touchActionMap[actionKey]
        val lastTime = lastTimeAction ?: 0

        if (currentTime - lastTime >= minTime) {
            lastTimeAction = currentTime
            touchActionMap[actionKey] = lastTimeAction
            result = true
        }

        return result
    }
}
