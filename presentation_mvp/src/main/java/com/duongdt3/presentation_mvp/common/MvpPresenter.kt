package com.duongdt3.presentation_mvp.common

import org.koin.core.KoinComponent
import java.lang.ref.WeakReference

abstract class MvpPresenter<V : MvpView> : KoinComponent {
    private var weakReference: WeakReference<V>? = null
    fun start(view: V) {
        if (!isViewAttached) {
            weakReference = WeakReference(view)
        }

        onStart()
    }

    protected abstract fun onStart()

    fun stop() {
        weakReference?.clear()
        weakReference = null

        onStop()
    }

    protected abstract fun onStop()

    val view: V?
        get() = weakReference?.get()

    private val isViewAttached: Boolean
        get() = weakReference?.get() != null
}