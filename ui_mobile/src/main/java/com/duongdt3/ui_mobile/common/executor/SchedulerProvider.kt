package com.duongdt3.ui_mobile.common.executor

import io.reactivex.Scheduler

interface SchedulerProvider {
    fun createScheduler(): Scheduler
}