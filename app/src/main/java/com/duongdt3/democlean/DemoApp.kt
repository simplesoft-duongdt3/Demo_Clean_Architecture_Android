package com.duongdt3.democlean

import androidx.multidex.MultiDexApplication
import com.duongdt3.di_koin.DiKoinRunner

class DemoApp : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        DiKoinRunner.initDi(this)
    }
}