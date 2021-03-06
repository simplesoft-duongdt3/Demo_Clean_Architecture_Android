package com.duongdt3.ui_mobile.common.executor

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

class AndroidPostTaskSchedulerProvider : SchedulerProvider {
    override fun createScheduler(): Scheduler = AndroidSchedulers.mainThread()
}