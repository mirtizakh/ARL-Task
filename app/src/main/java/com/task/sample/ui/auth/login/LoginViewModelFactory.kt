package com.task.sample.ui.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.task.sample.data.network.api_call.session.ISessionManager
import com.task.sample.util.ValidationInterface

@Suppress("UNCHECKED_CAST")
class LoginViewModelFactory(
    private var validation: ValidationInterface,
    private var sessionManager: ISessionManager
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(validation, sessionManager) as T
    }
}