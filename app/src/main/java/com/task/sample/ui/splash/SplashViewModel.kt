package com.task.sample.ui.splash

import com.task.sample.R
import com.task.sample.data.network.api_call.session.ISessionManager
import com.task.sample.ui.base.BaseViewModel

class SplashViewModel(
    private var sessionManager: ISessionManager
) : BaseViewModel<SplashFragmentNavigator>() {
    // region PUBLIC methods
    fun redirectedToScreen() {
        if (sessionManager.isLogin()) {
            getNavigator()?.redirectToScreen(R.id.action_splashFragment_to_dashboardFragment)
        } else {
            getNavigator()?.redirectToScreen(R.id.action_splashFragment_to_signupFragment)
        }
    }
    // end region PUBLIC methods
}