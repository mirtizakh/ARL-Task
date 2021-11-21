package com.task.sample.ui.base

import androidx.lifecycle.ViewModel
import java.lang.ref.WeakReference

open class BaseViewModel<N> : ViewModel() {
    lateinit var navigator: WeakReference<N>

    fun getNavigator(): N? {
        return navigator.get()
    }

    fun setNavigator(navigator: N?) {
        this.navigator = WeakReference(navigator)
    }
}