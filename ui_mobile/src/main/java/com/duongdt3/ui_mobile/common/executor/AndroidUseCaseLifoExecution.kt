package com.duongdt3.ui_mobile.common.executor

import com.duongdt3.domain.common.usecase.single.UseCaseExecution

class AndroidUseCaseLifoExecution : UseCaseExecution(AndroidTaskLifoSchedulerProvider().createScheduler(), AndroidPostTaskSchedulerProvider().createScheduler())