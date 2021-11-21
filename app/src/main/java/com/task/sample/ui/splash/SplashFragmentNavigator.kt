package com.task.sample.ui.splash

import com.task.sample.ui.base.BaseNavigator

interface SplashFragmentNavigator : BaseNavigator {
    fun redirectToScreen(redirectionId: Int)
}