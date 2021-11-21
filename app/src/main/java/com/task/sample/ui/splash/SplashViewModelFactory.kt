package com.task.sample.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.task.sample.data.network.api_call.session.ISessionManager

@Suppress("UNCHECKED_CAST")
class SplashViewModelFactory(
    private var sessionManager: ISessionManager
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SplashViewModel(sessionManager) as T
    }
}